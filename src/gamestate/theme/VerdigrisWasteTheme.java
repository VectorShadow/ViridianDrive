package gamestate.theme;

import gamestate.theme.feature.EmptyFeatureReader;
import gamestate.theme.feature.FeatureReader;

public class VerdigrisWasteTheme extends ViridianDriveTheme {
    protected static final short PROPERTY_DEFINITION_INDEX_VERDIGRIS_DUST = 2;
    protected static final short PROPERTY_DEFINITION_INDEX_WASTE_FLORA = 3;
    protected static final short PROPERTY_DEFINITION_INDEX_BLUE_STONE = 4;
    protected static final short PROPERTY_DEFINITION_INDEX_WASTE_FUNGUS_TREE = 5;

    protected static final short PROPERTY_DEFINITION_INDEX_ARENA_ENTRY_GATE = 8;
    protected static final short PROPERTY_DEFINITION_INDEX_ARENA_EXIT_GATE = 9;

    /**
     * This class itself needs no features.
     */
    public VerdigrisWasteTheme() {
        super(new EmptyFeatureReader());
    }

    /**
     * For subclasses only.
     */
    protected VerdigrisWasteTheme(FeatureReader featureReader) {
        super(featureReader);
    }

    @Override
    protected short getFloorTerrain(int terrainIndex) {
        switch (terrainIndex) {
            case 0:
                return PROPERTY_DEFINITION_INDEX_VERDIGRIS_DUST;
            case 1:
                return PROPERTY_DEFINITION_INDEX_WASTE_FLORA;
            default:
                throw new IllegalArgumentException("Unhandled terrainIndex " + terrainIndex);
        }
    }

    @Override
    protected short getWallTerrain(int terrainIndex) {
        switch (terrainIndex) {
            case 0:
                return PROPERTY_DEFINITION_INDEX_BLUE_STONE;
            case 1:
                return PROPERTY_DEFINITION_INDEX_WASTE_FUNGUS_TREE;
            default:
                return -1;
        }
    }

    @Override
    protected short getTravelTerrain(int terrainIndex) {
        switch (terrainIndex) {
            case 0:
                return PROPERTY_DEFINITION_INDEX_ARENA_EXIT_GATE;
            case 4:
                return PROPERTY_DEFINITION_INDEX_ARENA_ENTRY_GATE;
                default:
                    return -1;
        }
    }

    @Override
    protected short getUntypedTerrain(char terrainSymbol) {
        throw new IllegalStateException("Untyped Terrain for symbol " + terrainSymbol + " not available.");
    }
}
