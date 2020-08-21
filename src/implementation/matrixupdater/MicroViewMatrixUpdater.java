package implementation.matrixupdater;

import definitions.DefinitionsManager;
import definitions.ViridianDriveColors;
import frontend.io.GUIConstants;
import gamestate.coordinates.Coordinate;
import gamestate.coordinates.PointCoordinate;
import gamestate.gameobject.actor.ViridianDriveActor;
import gamestate.terrain.TerrainTile;
import images.ImageMatrix;
import images.TextImageSource;
import user.PlayerSession;

import static definitions.ViridianDriveColors.*;

public class MicroViewMatrixUpdater extends MatrixUpdater {
    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        ViridianDriveActor playerActor = (ViridianDriveActor)PlayerSession.getActor();
        if (playerActor != null && playerActor.getAt() != null){
            PointCoordinate gameZonePointCoordinate;
            Coordinate gameZoneTileCoordinate;
            TerrainTile terrainTile;
            TextImageSource textImageSource;
            for (int row = 0; row < imageMatrix.getMatrixHeight(); ++row) {
                for (int col = 0; col < imageMatrix.getMatrixWidth(); ++col) {
                    gameZonePointCoordinate = microToGameZone(col, row);
                    gameZoneTileCoordinate = gameZonePointCoordinate.getParentTileCoordinate();
                    if (
                            row == 0 || //draw a border around the micro view
                            row == imageMatrix.getMatrixHeight() - 1 ||
                            col == 0 ||
                            col == imageMatrix.getMatrixWidth() - 1
                    )
                            textImageSource =
                                    new TextImageSource(
                                            ViridianDriveColors.DISPLAY_FOREGROUND_0,
                                            ViridianDriveColors.DISPLAY_FOREGROUND_0,
                                            ' '
                                    );
                    else if (gameZonePointCoordinate.equals(playerActor.getAt())) //check for the player
                        textImageSource = new TextImageSource(STATUS_INFO, DISPLAY_FOREGROUND_0, ' ');
                    else if (PlayerSession.getZoneKnowledge().isRemembered(gameZoneTileCoordinate)) { //check memory
                        terrainTile = PlayerSession.getZoneKnowledge().getGameZone().tileAt(gameZoneTileCoordinate);
                        if (!DefinitionsManager.getTerrainLookup().checkAccess(playerActor, terrainTile)) //check for impassable terrain
                            textImageSource = new TextImageSource(STATUS_ERROR, DISPLAY_FOREGROUND_0, ' ');
                        else if (PlayerSession.getZoneKnowledge().isRevealed(gameZoneTileCoordinate)) { //check for known features
                            if (terrainTile.terrainFeature.isAutoTriggered()) //warn for automatically triggered features like traps
                                textImageSource = new TextImageSource(STATUS_WARNING, DISPLAY_FOREGROUND_0, ' ');
                            else //otherwise indicate they are player triggered
                                textImageSource = new TextImageSource(STATUS_SUCCESS, DISPLAY_BACKGROUND_0, ' ');
                        }
                        else if (DefinitionsManager.getTerrainLookup().getProperties(terrainTile).TRAVEL_PERMISSION > 0) //check for terrain which allows travel
                            textImageSource = new TextImageSource(STATUS_ALERT, DISPLAY_BACKGROUND_0, ' ');
                        else
                            textImageSource = new TextImageSource(DISPLAY_BACKGROUND_0, DISPLAY_FOREGROUND_0, ' ');
                    } else
                        textImageSource = new TextImageSource(STATUS_PENDING, DISPLAY_BACKGROUND_0, ' ');
                    imageMatrix.set(row, col, textImageSource);
                }
            }
        }
        return imageMatrix;
    }

    private PointCoordinate microToGameZone(int viewCol, int viewRow) {
        PointCoordinate playerAt = PlayerSession.getActor().getAt();
        int x = playerAt.COLUMN;
        int y = playerAt.ROW;
        int regionMidPointX = GUIConstants.CH_MAIN_RG_MICRO_WIDTH / 2;
        int regionMidPointY = GUIConstants.CH_MAIN_RG_MICRO_HEIGHT / 2;
        int xOffset = viewCol - regionMidPointX;
        int yOffset = viewRow - regionMidPointY;
        return new PointCoordinate(x + xOffset, y + yOffset);
    }
}
