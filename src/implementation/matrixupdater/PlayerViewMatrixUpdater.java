package implementation.matrixupdater;

import definitions.DefinitionsManager;
import frontend.PlayerSession;
import frontend.io.GUIConstants;
import gamestate.coordinates.Coordinate;
import gamestate.gamezone.GameZone;
import gamestate.terrain.ViridianDriveTerrainProperties;
import images.ImageMatrix;
import images.ImageSource;
import images.TextImageSource;

import java.awt.*;

public class PlayerViewMatrixUpdater extends MatrixUpdater {
    private static final int SUPPORTED_LAYERS = 2;

    public PlayerViewMatrixUpdater() {
        super(SUPPORTED_LAYERS);
    }

    @Override
    protected ImageMatrix doUpdate() {
        ImageMatrix imageMatrix = LAYERS[currentLayer];
        if (PlayerSession.getPlayerActor().getAt() != null) {
            for (int i = 0; i < imageMatrix.getMatrixHeight(); ++i) {
                for (int j = 0; j < imageMatrix.getMatrixWidth(); ++j) {
                    imageMatrix.set(
                            i,
                            j,
                            imageAt(j, i)
                    );
                }
            }
        }
        //hack - draw the player - this should be done from gameZone's terrain tile map, by each tile's actor list.

        return imageMatrix;
    }

    private ImageSource imageAt(int viewCol, int viewRow) {
        GameZone gameZone = GameZone.frontEnd;
        Coordinate gameZoneCoordinate = viewToGameZone(viewCol, viewRow);
        int x = gameZoneCoordinate.COLUMN;
        int y = gameZoneCoordinate.ROW;
        if (x < 0 || x >= gameZone.countColumns() || y < 0 || y >= gameZone.countRows())
            return null;
        switch (currentLayer) {
            case 0: //terrain layer
                return (
                        (ViridianDriveTerrainProperties)DefinitionsManager.
                                getTerrainLookup().
                                getProperties(
                                        gameZone.tileAt(gameZoneCoordinate)
                                )
                ).getImageSource();
            case 1: //for now, actor layer. if we had items, features, etc., those should draw before actors
                //todo - this is a hack, we simply draw an @ if some actor is here
                return gameZone.tileAt(gameZoneCoordinate).actorList.isEmpty() ? null : new TextImageSource(Color.BLACK, Color.WHITE, '@');
            //todo - more cases here, projectiles probably
                default:
                    throw new IllegalStateException();
        }
    }

    private Coordinate viewToGameZone(int viewCol, int viewRow) {
        Coordinate playerAt = PlayerSession.getPlayerActor().getAt().getParentTileCoordinate();
        int x = playerAt.COLUMN;
        int y = playerAt.ROW;
        int regionMidPointX = GUIConstants.REGION_VIEW_WIDTH / 2;
        int regionMidPointY = GUIConstants.REGION_VIEW_HEIGHT / 2;
        int xOffset = viewCol - regionMidPointX;
        int yOffset = viewRow - regionMidPointY;
        return new Coordinate(x + xOffset, y + yOffset);
    }
}
