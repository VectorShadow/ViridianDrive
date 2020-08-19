package gamestate.theme;

public class VerdigrisWasteTownTheme extends TownTheme{

    private static final short PROPERTY_DEFINITION_INDEX_VERDIGRIS_DUST = 33;
    private static final short PROPERTY_DEFINITION_INDEX_WASTE_FLORA = 34;
    private static final short PROPERTY_DEFINITION_INDEX_BLUE_STONE = 35;
    private static final short PROPERTY_DEFINITION_INDEX_WASTE_FUNGUS_TREE = 36;
    private static final short PROPERTY_DEFINITION_INDEX_BLUE_STONE_BRICK = 37;
    private static final short PROPERTY_DEFINITION_INDEX_TAR_PAVED_ROAD = 38;

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
            case 2:
                return PROPERTY_DEFINITION_INDEX_BLUE_STONE_BRICK;
                default:
                    throw new IllegalArgumentException("Unhandled terrainIndex " + terrainIndex);
        }
    }

    @Override
    protected short getSpecialTerrain(int terrainIndex) {
        return PROPERTY_DEFINITION_INDEX_TAR_PAVED_ROAD;
    }
}
