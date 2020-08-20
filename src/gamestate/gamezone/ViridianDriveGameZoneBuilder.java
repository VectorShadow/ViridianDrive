package gamestate.gamezone;

import gamestate.terrain.TerrainTile;
import gamestate.theme.ViridianDriveTheme;

public abstract class ViridianDriveGameZoneBuilder extends GameZoneBuilder {

    private static final short PROPERTY_DEFINITION_INDEX_MAP_BOUNDARY = 0;

    private final int HEIGHT;
    private final int WIDTH;

    protected final ViridianDriveTheme THEME;

    protected final GameZone ZONE;

    public ViridianDriveGameZoneBuilder(int height, int width, ViridianDriveTheme theme) {
        HEIGHT = height;
        WIDTH = width;
        THEME = theme;
        ZONE = new GameZone(HEIGHT, WIDTH);
    }

    @Override
    public GameZone build() {
        for (int i = 0; i < ZONE.ROWS; ++i) {
            for (int j = 0; j < ZONE.COLUMNS; ++j) {
                ZONE.TERRAIN[i][j] =
                        i == 0 || i == ZONE.ROWS - 1 || j == 0 || j == ZONE.COLUMNS - 1 ?
                                new TerrainTile(PROPERTY_DEFINITION_INDEX_MAP_BOUNDARY) : //map boundaries on the perimeter
                                generateTile(i, j); //else get a tile from the implementation
            }
        }
        return ZONE;
    }

    /**
     * Generate a tile appropriate for the specified location in the implementation.
     * This should also set all necessary tile fields, such as features and actors.
     */
    protected abstract TerrainTile generateTile(int row, int column);
}
