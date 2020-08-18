package gamestate.gamezone;

import gamestate.terrain.TerrainTile;

public class TestGameZoneBuilder extends ViridianDriveGameZoneBuilder {

    private static final short PROPERTY_DEFINITION_INDEX_EMPTY_TILE = 1;

    public TestGameZoneBuilder() {
        super(32, 32, null);
    }

    @Override
    protected TerrainTile generateTile(int row, int column) {
        return new TerrainTile(PROPERTY_DEFINITION_INDEX_EMPTY_TILE);
    }
}
