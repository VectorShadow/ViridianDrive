package implementation.matrixupdater;

import definitions.DefinitionsManager;
import frontend.io.GUIConstants;
import gamestate.coordinates.Coordinate;
import gamestate.gamezone.GameZone;
import gamestate.terrain.TerrainProperties;
import gamestate.terrain.ViridianDriveTerrainProperties;
import images.ImageMatrix;
import images.ImageSource;
import images.TextImageSource;
import user.PlayerSession;
import util.Direction;

import java.awt.*;
import java.util.ArrayList;

public class PlayerViewMatrixUpdater extends MatrixUpdater {

    private static ArrayList<Coordinate> visibleTiles = new ArrayList<>();
    private static GameZone playerGameZone = null;
    private static boolean[][] rememberedTiles;

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
        if (
                gameZoneColumn < 0 ||
                        gameZoneRow < 0 ||
                        gameZoneColumn >= playerGameZone.countColumns() ||
                        gameZoneRow >= playerGameZone.countRows() ||
                        !rememberedTiles[gameZoneRow][gameZoneColumn]
        )
            return null;
        if (!visibleTiles.contains(gameZoneCoordinate)) {
            return
            ((ViridianDriveTerrainProperties)DefinitionsManager.
                    getTerrainLookup().
                    getProperties(
                            playerGameZone.tileAt(gameZoneCoordinate)
                    )
                ).getMemoryImageSource();
        }
        switch (currentLayer) {
            case 0: //terrain layer
                return (
                        (ViridianDriveTerrainProperties)DefinitionsManager.
                                getTerrainLookup().
                                getProperties(
                                        playerGameZone.tileAt(gameZoneCoordinate)
                                )
                ).getVisibleImageSource();
            case 1: //for now, actor layer. if we had items, features, etc., those should draw before actors
                //todo - this is a hack, we simply draw an @ if some actor is here
                return
                        playerGameZone.tileAt(gameZoneCoordinate).actorList.isEmpty()
                                ? null
                                : new TextImageSource(
                                        layers[0]
                                                .getBackgroundColorAt(
                                                        viewRow,
                                                        viewCol
                                                ),
                                        Color.WHITE,
                                        '@'
                                );
            //todo - more cases here, projectiles probably
                default:
                    throw new IllegalStateException();
        }
    }

    private Coordinate viewToGameZone(int viewCol, int viewRow) {
        Coordinate playerAt = PlayerSession.getActor().getAt().getParentTileCoordinate();
        int x = playerAt.COLUMN;
        int y = playerAt.ROW;
        int regionMidPointX = GUIConstants.REGION_VIEW_WIDTH / 2;
        int regionMidPointY = GUIConstants.REGION_VIEW_HEIGHT / 2;
        int xOffset = viewCol - regionMidPointX;
        int yOffset = viewRow - regionMidPointY;
        return new Coordinate(x + xOffset, y + yOffset);
    }

    private static void updateVisionAndMemory() {
        if (playerGameZone == null || playerGameZone != GameZone.frontEnd) {
            playerGameZone = GameZone.frontEnd;
            rememberedTiles = new boolean[playerGameZone.countRows()][playerGameZone.countColumns()];
            for (int r = 0; r < playerGameZone.countRows(); ++r) {
                for (int c = 0; c < playerGameZone.countColumns(); ++c){
                    rememberedTiles[r][c] = false;
                }
            }
        }
        visibleTiles = new ArrayList<>();
        Coordinate playerAt = PlayerSession.getActor().getAt().getParentTileCoordinate();
        visibleTiles.add(playerAt);
        for (Direction direction : Direction.values()) {
            if (direction == Direction.SELF) continue;
            Coordinate c = new Coordinate(playerAt, direction);
            radiateSight(8.0, direction, c); //todo - derive sight power from player's actor. have power derive from gamezone light level?
        }
    }

    private static void radiateSight(double sightPower, Direction primaryDirection, Coordinate radiateFrom) {
        visibleTiles.add(radiateFrom);
        if (
                radiateFrom.ROW >= 0 &&
                        radiateFrom.ROW < playerGameZone.countRows() &&
                        radiateFrom.COLUMN >= 0 &&
                        radiateFrom.COLUMN < playerGameZone.countColumns()
        )
            rememberedTiles[radiateFrom.ROW][radiateFrom.COLUMN] = true;
        sightPower -= primaryDirection.isDiagonal() ? 1.5 : 1.0;
        if (
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
        radiateSight(sightPower, primaryDirection.rotateClockwise(), new Coordinate(radiateFrom, primaryDirection.rotateClockwise()));
        radiateSight(sightPower, primaryDirection, new Coordinate(radiateFrom, primaryDirection));
        radiateSight(sightPower, primaryDirection.rotateCounterClockwise(), new Coordinate(radiateFrom, primaryDirection.rotateCounterClockwise()));
    }
}
