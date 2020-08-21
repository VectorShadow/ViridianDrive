package implementation.matrixupdater;

import backend.EngineManager;
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
import link.instructions.UpdatePlayerMemoryInstructionDatum;
import user.PlayerSession;
import util.Direction;

import java.util.ArrayList;

public class PlayerViewMatrixUpdater extends MatrixUpdater {

    private static ArrayList<Coordinate> visibleTiles = new ArrayList<>();

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
        Imageable imageable;
        if (!PlayerSession.getZoneKnowledge().isRemembered(gameZoneCoordinate))
            return null;
        switch (currentLayer) {
            case 0: //terrain layer
                imageable =
                        (ViridianDriveTerrainProperties)
                                DefinitionsManager.
                                getTerrainLookup().
                                getProperties(
                                        PlayerSession.
                                                getZoneKnowledge().
                                                getGameZone().
                                                tileAt(gameZoneCoordinate));
                break;
            case 1:
                ViridianDriveTerrainFeature terrainFeature =
                        (ViridianDriveTerrainFeature) (
                                PlayerSession.
                                        getZoneKnowledge().
                                        getGameZone().
                                        tileAt(gameZoneCoordinate).
                                        terrainFeature
                                );
                imageable = PlayerSession.getZoneKnowledge().isRevealed(gameZoneCoordinate)
                        ? terrainFeature
                        : null;
                break;
            case 2: //for now, actor layer. if we have items, etc., those should draw before actors
                ArrayList<MobileGameObject> actors =
                        PlayerSession.getZoneKnowledge().getGameZone().tileAt(gameZoneCoordinate).actorList;
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
        visibleTiles = new ArrayList<>();
        Coordinate playerAt = PlayerSession.getActor().getAt().getParentTileCoordinate();
        double[] arcs = ((ViridianDriveActor)PlayerSession.getActor()).getVisionArcs();
        for (Direction direction : Direction.values()) {
            if (arcs[direction.ordinal()] >= 1.0) {
                if (direction == Direction.SELF)
                    visibleTiles.add(playerAt);
                else {
                    Coordinate c = new Coordinate(playerAt, direction);
                    radiateSight(arcs[direction.ordinal()], direction, c); //todo - derive sight power from player's actor. have power derive from gamezone light level?
                }
            }
        }
        ArrayList<Coordinate> previouslyUnknownTileCoordinates =
                PlayerSession.getZoneKnowledge().rememberTiles(visibleTiles);
        if (previouslyUnknownTileCoordinates.size() > 0)
            EngineManager.frontEndDataLink.transmit(
                    new UpdatePlayerMemoryInstructionDatum(
                            PlayerSession.getZoneKnowledge().getMemoryChecksum(),
                            false,
                            previouslyUnknownTileCoordinates
                    )
            );
    }

    private static void radiateSight(double sightPower, Direction primaryDirection, Coordinate radiateFrom) {
        if (PlayerSession.getZoneKnowledge().isInBounds(radiateFrom))
            visibleTiles.add(radiateFrom);
        if ( //if remaining sight power is not sufficient to see beyond this tile, stop
                sightPower < 1.0 ||
                        DefinitionsManager.
                                getTerrainLookup().
                                getProperties(
                                        PlayerSession.
                                                getZoneKnowledge().
                                                getGameZone().
                                                tileAt(radiateFrom)
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
}
