package implementation.matrixupdater;

import definitions.DefinitionsManager;
import frontend.io.GUIConstants;
import frontend.io.Imageable;
import gamestate.coordinates.Coordinate;
import gamestate.gameobject.MobileGameObject;
import gamestate.gameobject.actor.ViridianDriveActor;
import gamestate.gamezone.GameZone;
import gamestate.terrain.TerrainProperties;
import gamestate.terrain.ViridianDriveTerrainFeature;
import gamestate.terrain.ViridianDriveTerrainProperties;
import images.ImageMatrix;
import images.ImageSource;
import user.PlayerSession;
import util.Direction;

import java.util.ArrayList;

public class PlayerViewMatrixUpdater extends MatrixUpdater {

    private static ArrayList<Coordinate> visibleTiles = new ArrayList<>();
    static GameZone playerGameZone = null;
    static boolean[][] rememberedTiles;
    static boolean[][] revealedFeatures;

    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        if (PlayerSession.getActor() != null && PlayerSession.getActor().getAt() != null) {
            updateVisionAndMemory();
            for (int i = 0; i < imageMatrix.getMatrixHeight(); ++i) {
                for (int j = 0; j < imageMatrix.getMatrixWidth(); ++j) {
                    imageMatrix.set(
                            i,
                            j,
                            imageAt(j, i, currentLayer)
                    );
                }
            }
        }
        return imageMatrix;
    }

    private ImageSource imageAt(int viewCol, int viewRow, int currentLayer) {
        Coordinate gameZoneCoordinate = viewToGameZone(viewCol, viewRow);
        int gameZoneColumn = gameZoneCoordinate.COLUMN;
        int gameZoneRow = gameZoneCoordinate.ROW;
        Imageable imageable;
        if (
                gameZoneColumn < 0 ||
                        gameZoneRow < 0 ||
                        gameZoneColumn >= playerGameZone.countColumns() ||
                        gameZoneRow >= playerGameZone.countRows() ||
                        !rememberedTiles[gameZoneRow][gameZoneColumn]
        )
            return null;
        switch (currentLayer) {
            case 0: //terrain layer
                imageable =
                        (ViridianDriveTerrainProperties)
                                DefinitionsManager.
                                getTerrainLookup().
                                getProperties(playerGameZone.tileAt(gameZoneCoordinate));
                break;
            case 1:
                ViridianDriveTerrainFeature terrainFeature =
                        (ViridianDriveTerrainFeature)
                                (playerGameZone.tileAt(gameZoneCoordinate).terrainFeature);
                imageable =
                        (terrainFeature == null ||
                                (terrainFeature.isHidden() && !revealedFeatures[gameZoneRow][gameZoneColumn]))
                        ? null
                        : terrainFeature;
                break;
            case 2: //for now, actor layer. if we have items, etc., those should draw before actors
                ArrayList<MobileGameObject> actors = playerGameZone.tileAt(gameZoneCoordinate).actorList;
                imageable = actors.isEmpty() ? null : (ViridianDriveActor)actors.get(0);
                break;
            //todo - more cases here, larger/slower projectiles probably
            //todo - visual effects?
                default:
                    throw new IllegalStateException();
        }
        return imageable == null
                ? null
                : visibleTiles.contains(gameZoneCoordinate)
                ? imageable.getVisibleImageSource()
                : imageable.getMemoryImageSource();
    }

    private Coordinate viewToGameZone(int viewCol, int viewRow) {
        Coordinate playerAt = PlayerSession.getActor().getAt().getParentTileCoordinate();
        int x = playerAt.COLUMN;
        int y = playerAt.ROW;
        int regionMidPointX = GUIConstants.CH_MAIN_RG_VIEW_WIDTH / 2;
        int regionMidPointY = GUIConstants.CH_MAIN_RG_VIEW_HEIGHT / 2;
        int xOffset = viewCol - regionMidPointX;
        int yOffset = viewRow - regionMidPointY;
        return new Coordinate(x + xOffset, y + yOffset);
    }

    private static void updateVisionAndMemory() {
        if (playerGameZone == null || playerGameZone != GameZone.frontEnd) {
            playerGameZone = GameZone.frontEnd;
            rememberedTiles = new boolean[playerGameZone.countRows()][playerGameZone.countColumns()];
            revealedFeatures = new boolean[playerGameZone.countRows()][playerGameZone.countColumns()];
            for (int r = 0; r < playerGameZone.countRows(); ++r) {
                for (int c = 0; c < playerGameZone.countColumns(); ++c){
                    rememberedTiles[r][c] = false;
                }
            }
        }
        visibleTiles = new ArrayList<>();
        Coordinate playerAt = PlayerSession.getActor().getAt().getParentTileCoordinate();
        visibleTiles.add(playerAt);
        rememberedTiles[playerAt.ROW][playerAt.COLUMN] = true;
        for (Direction direction : Direction.values()) {
            if (direction == Direction.SELF) continue;
            Coordinate c = new Coordinate(playerAt, direction);
            radiateSight(8.0, direction, c); //todo - derive sight power from player's actor. have power derive from gamezone light level?
        }
    }

    private static void radiateSight(double sightPower, Direction primaryDirection, Coordinate radiateFrom) {
        if ( //add this tile to sight and memory if it is within the game zone
                radiateFrom.ROW >= 0 &&
                        radiateFrom.ROW < playerGameZone.countRows() &&
                        radiateFrom.COLUMN >= 0 &&
                        radiateFrom.COLUMN < playerGameZone.countColumns()
        ){
            visibleTiles.add(radiateFrom);
            rememberedTiles[radiateFrom.ROW][radiateFrom.COLUMN] = true;
        }
        if ( //if remaining sight power is not sufficient to see beyond tthis tile, stop
                sightPower < 1.0 ||
                        DefinitionsManager.
                                getTerrainLookup().
                                getProperties(
                                        GameZone.
                                                frontEnd.
                                                tileAt(
                                                        radiateFrom
                                                )
                                ).
                                ENERGY_PERMISSION == TerrainProperties.ENERGY_PERMISSION_OPAQUE
        )
            return;
        Direction dir;
        for (int i = 0; i < 3; ++i) { //attempt to view the next 3 tiles in the direction of radiation
            dir =
                    i == 0
                            ? primaryDirection.rotateClockwise()
                            : i == 1
                            ? primaryDirection
                            : primaryDirection.rotateCounterClockwise();
            radiateSight(
                    dir.isDiagonal() ? sightPower - 1.5 : sightPower - 1.0, //lose more sight power on a diagonal
                    primaryDirection, //continue in the current direction
                    new Coordinate(radiateFrom, dir)
            );
        }
    }

    /**
     * Call this when we need to make hidden features visible to the player, either by accidentally activating them,
     * or by some detection ability.
     */
    public static void revealFeature(Coordinate gameZoneCoordinate) {
        revealedFeatures[gameZoneCoordinate.ROW][gameZoneCoordinate.COLUMN] = true;
    }
}
