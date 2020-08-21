package implementation.matrixupdater;

import definitions.DefinitionsManager;
import definitions.ViridianDriveColors;
import frontend.io.GUIConstants;
import gamestate.coordinates.Coordinate;
import gamestate.terrain.ViridianDriveTerrainFeature;
import gamestate.terrain.ViridianDriveTerrainProperties;
import images.ImageMatrix;
import images.ImageSource;
import images.TextImageSource;
import user.PlayerSession;
import user.ZoneKnowledge;

import java.awt.*;

public class MinimapMatrixUpdater extends MatrixUpdater {

    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        if (PlayerSession.getActor() != null && PlayerSession.getActor().getAt() != null) {
            Coordinate playerCoordinate = PlayerSession.getActor().getAt().getParentTileCoordinate();
            Coordinate tileCoordinate;
            ImageSource tileImageSource;
            ZoneKnowledge zoneKnowledge = PlayerSession.getZoneKnowledge();
            for (int row = 0; row < GUIConstants.CH_MAIN_RG_MINIMAP_HEIGHT; ++ row) {
                for (int col = 0; col < GUIConstants.CH_MAIN_RG_MINIMAP_WIDTH; ++col) {
                    tileCoordinate = minimapToGameZone(col, row);
                    tileImageSource =
                            zoneKnowledge.isRemembered(tileCoordinate)
                                    ? zoneKnowledge.isRevealed(tileCoordinate)
                                    ? ((ViridianDriveTerrainFeature)
                                    zoneKnowledge.getGameZone().tileAt(tileCoordinate).terrainFeature
                                    ).getVisibleImageSource() //show revealed terrain features
                                    : ((ViridianDriveTerrainProperties)
                                    (DefinitionsManager.getTerrainLookup().getProperties(
                                            zoneKnowledge.getGameZone().tileAt(tileCoordinate))
                                    )).getVisibleImageSource() //otherwise show terrain
                                    : null; //otherwise show nothing
                    imageMatrix.set(
                            row,
                            col,
                            row == 0 || //draw a border around the minimap
                                    row == GUIConstants.CH_MAIN_RG_MINIMAP_HEIGHT - 1 ||
                                    col == 0 ||
                                    col == GUIConstants.CH_MAIN_RG_MINIMAP_WIDTH - 1
                                    ? new TextImageSource(
                                        ViridianDriveColors.DISPLAY_FOREGROUND_0,
                                        ViridianDriveColors.DISPLAY_FOREGROUND_0,
                                        ' '
                                    )
                                    : playerCoordinate.isAdjacentTo(tileCoordinate) //draw a 3x3 representation of the player to ensure his location is clearly marked
                                    ? new TextImageSource(
                                        Color.WHITE,
                                        ViridianDriveColors.DISPLAY_FOREGROUND_0,
                                        ' '
                                    )
                                    : tileImageSource == null
                                    ? null //show nothing
                                    : new TextImageSource(
                                            tileImageSource.getBackgroundColor(),
                                            ViridianDriveColors.DISPLAY_FOREGROUND_0,
                                        ' '
                                    ) //show an image source based on the actual image source's background only - much too small to show symbols
                    );
                }
            }
        }
        return imageMatrix;
    }

    private Coordinate minimapToGameZone(int miniMapColumn, int miniMapRow) {
        Coordinate playerAt = PlayerSession.getActor().getAt().getParentTileCoordinate();
        int x = playerAt.COLUMN;
        int y = playerAt.ROW;
        int regionMidPointX = GUIConstants.CH_MAIN_RG_MINIMAP_WIDTH / 2;
        int regionMidPointY = GUIConstants.CH_MAIN_RG_MINIMAP_HEIGHT / 2;
        int xOffset = miniMapColumn - regionMidPointX;
        int yOffset = miniMapRow - regionMidPointY;
        return new Coordinate(x + xOffset, y + yOffset);
    }
}
