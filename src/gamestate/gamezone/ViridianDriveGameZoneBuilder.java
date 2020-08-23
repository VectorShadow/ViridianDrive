package gamestate.gamezone;

import definitions.DefinitionsManager;
import gamestate.coordinates.Coordinate;
import gamestate.terrain.TerrainProperties;
import gamestate.terrain.TerrainTile;
import gamestate.theme.ViridianDriveTheme;

public abstract class ViridianDriveGameZoneBuilder extends GameZoneBuilder {

    private static final short PROPERTY_DEFINITION_INDEX_MAP_BOUNDARY = 0;

    protected final ViridianDriveTheme THEME;

    public ViridianDriveGameZoneBuilder(int size, ViridianDriveTheme theme) {
        this(size, size, theme);
    }

    public ViridianDriveGameZoneBuilder(int height, int width, ViridianDriveTheme theme) {
        super(height, width);
        THEME = theme;
    }

    @Override
    public GameZone build() {
        for (int i = 0; i < ZONE.ROWS; ++i) {
            for (int j = 0; j < ZONE.COLUMNS; ++j) {
                if (i == 0 || i == ZONE.ROWS - 1 || j == 0 || j == ZONE.COLUMNS - 1)
                    ZONE.TERRAIN[i][j] = new TerrainTile(PROPERTY_DEFINITION_INDEX_MAP_BOUNDARY); //map boundaries on the perimeter
                else
                    setTerrainTile(i, j); //else set a tile based on the implementation
            }
        }
        return ZONE;
    }

    /**
     * Test each terrain tile for travel permissions and mark the zone as necessary.
     * All implementations of generateTile should call this.
     */
    protected void setTravelPoints(int row, int column, TerrainTile terrainTile) {
        int travelPermission = DefinitionsManager.getTerrainLookup().getProperties(terrainTile).TRAVEL_PERMISSION;
        if (travelPermission == TerrainProperties.TRAVEL_PERMISSION_NONE) return;
        ZONE.TRAVEL_POINTS.setTravelPointTo(travelPermission, new Coordinate(column, row));
    }
}
