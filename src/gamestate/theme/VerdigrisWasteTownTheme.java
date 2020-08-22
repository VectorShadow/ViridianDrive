package gamestate.theme;

import gamestate.theme.feature.TownFeatureReader;

public class VerdigrisWasteTownTheme extends VerdigrisWasteTheme {

    protected static final short PROPERTY_DEFINITION_INDEX_BLUE_STONE_BRICK = 6;
    protected static final short PROPERTY_DEFINITION_INDEX_TAR_PAVED_ROAD = 7;

    public VerdigrisWasteTownTheme() {
        super(new TownFeatureReader());
    }

    @Override
    protected short getWallTerrain(int terrainIndex) {
        short superIndex = super.getWallTerrain(terrainIndex);
        if (superIndex >= 0) return superIndex;
        switch (terrainIndex) {
            case 2:
                return PROPERTY_DEFINITION_INDEX_BLUE_STONE_BRICK;
                default:
                    throw new IllegalArgumentException("Unhandled terrainIndex " + terrainIndex);
        }
    }

    @Override
    protected short getUntypedTerrain(char terrainSymbol) {
        return PROPERTY_DEFINITION_INDEX_TAR_PAVED_ROAD;
    }
}
