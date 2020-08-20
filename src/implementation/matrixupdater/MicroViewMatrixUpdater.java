package implementation.matrixupdater;

import definitions.DefinitionsManager;
import frontend.io.GUIConstants;
import gamestate.coordinates.Coordinate;
import gamestate.coordinates.PointCoordinate;
import gamestate.gameobject.actor.ViridianDriveActor;
import gamestate.gamezone.GameZone;
import images.ImageMatrix;
import images.TextImageSource;
import user.PlayerSession;

import java.awt.*;

import static definitions.ViridianDriveColors.*;

public class MicroViewMatrixUpdater extends MatrixUpdater {
    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        ViridianDriveActor playerActor = (ViridianDriveActor)PlayerSession.getActor();
        if (playerActor != null && playerActor.getAt() != null){
            PointCoordinate gameZonePointCoordinate;
            TextImageSource textImageSource;
            for (int row = 0; row < imageMatrix.getMatrixHeight(); ++row) {
                for (int col = 0; col < imageMatrix.getMatrixWidth(); ++col) {
                    gameZonePointCoordinate = microToGameZone(col, row);
                    if (gameZonePointCoordinate.equals(playerActor.getAt()))
                        textImageSource = new TextImageSource(STATUS_SUCCESS, DISPLAY_FOREGROUND_0, ' ');
                    else if (
                            inBounds(gameZonePointCoordinate) &&
                            DefinitionsManager
                                    .getTerrainLookup()
                                    .checkAccess(
                                            playerActor,
                                            GameZone.frontEnd.tileAt(gameZonePointCoordinate.getParentTileCoordinate())
                                    )
                    )
                        textImageSource = new TextImageSource(DISPLAY_BACKGROUND_0, DISPLAY_FOREGROUND_0, ' ');
                    else
                        textImageSource = new TextImageSource(STATUS_ERROR, DISPLAY_FOREGROUND_0, ' ');
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

    private boolean inBounds(PointCoordinate pointCoordinate) {
        Coordinate gameZoneCoordinate = pointCoordinate.getParentTileCoordinate();
        int gameZoneColumn = gameZoneCoordinate.COLUMN;
        int gameZoneRow = gameZoneCoordinate.ROW;
        return
                gameZoneColumn >= 0 &&
                        gameZoneRow >= 0 &&
                        gameZoneColumn < GameZone.frontEnd.countColumns() &&
                        gameZoneRow < GameZone.frontEnd.countRows();
    }
}
