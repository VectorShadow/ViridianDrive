package implementation.matrixupdater;

import definitions.DefinitionsManager;
import frontend.io.GUIConstants;
import gamestate.coordinates.Coordinate;
import gamestate.coordinates.PointCoordinate;
import gamestate.gameobject.actor.ViridianDriveActor;
import gamestate.terrain.TerrainTile;
import images.ImageMatrix;
import images.TextImageSource;
import user.PlayerSession;

import static definitions.ViridianDriveColors.*;

import static implementation.matrixupdater.PlayerViewMatrixUpdater.*;

public class MicroViewMatrixUpdater extends MatrixUpdater {
    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        ViridianDriveActor playerActor = (ViridianDriveActor)PlayerSession.getActor();
        if (playerActor != null && playerActor.getAt() != null){
            PointCoordinate gameZonePointCoordinate;
            Coordinate gameZoneTileCoordinate;
            int gameZoneRow, gameZoneCol;
            TerrainTile terrainTile;
            TextImageSource textImageSource;
            for (int row = 0; row < imageMatrix.getMatrixHeight(); ++row) {
                for (int col = 0; col < imageMatrix.getMatrixWidth(); ++col) {
                    gameZonePointCoordinate = microToGameZone(col, row);
                    gameZoneTileCoordinate = gameZonePointCoordinate.getParentTileCoordinate();
                    gameZoneRow = gameZoneTileCoordinate.ROW;
                    gameZoneCol = gameZoneTileCoordinate.COLUMN;
                    if (gameZonePointCoordinate.equals(playerActor.getAt())) //check for the player
                        textImageSource = new TextImageSource(STATUS_INFO, DISPLAY_FOREGROUND_0, ' ');
                    else if ( //check bounds and memory
                            inBounds(gameZoneCol, gameZoneRow) &&
                                    rememberedTiles[gameZoneRow][gameZoneCol]
                    ) {
                        terrainTile = playerGameZone.tileAt(gameZoneTileCoordinate);
                        if (!DefinitionsManager.getTerrainLookup().checkAccess(playerActor, terrainTile)) //check for impassable terrain
                            textImageSource = new TextImageSource(STATUS_ERROR, DISPLAY_FOREGROUND_0, ' ');
                        else if ( //check for known features
                                terrainTile.terrainFeature != null &&
                                        (!terrainTile.terrainFeature.isHidden() ||
                                        revealedFeatures[gameZoneRow][gameZoneCol])
                        )
                            textImageSource = new TextImageSource(STATUS_SUCCESS, DISPLAY_FOREGROUND_0, ' ');
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

    private boolean inBounds(int gameZoneColumn, int gameZoneRow) {
        return
                gameZoneColumn >= 0 &&
                        gameZoneRow >= 0 &&
                        gameZoneColumn < playerGameZone.countColumns() &&
                        gameZoneRow < playerGameZone.countRows();
    }
}
