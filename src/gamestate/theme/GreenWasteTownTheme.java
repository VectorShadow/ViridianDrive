package gamestate.theme;

public class GreenWasteTownTheme extends ViridianDriveTheme {

    private static final short PROPERTY_DEFINITION_INDEX_GREEN_DUST = 2;
    private static final short PROPERTY_DEFINITION_INDEX_WASTE_FLORA = 3;
    private static final short PROPERTY_DEFINITION_INDEX_GREEN_STONE = 4;
    private static final short PROPERTY_DEFINITION_INDEX_WASTE_FUNGUS_TREE = 5;
    private static final short PROPERTY_DEFINITION_INDEX_GREEN_STONE_BRICK = 6;
    private static final short PROPERTY_DEFINITION_INDEX_TAR_PAVED_ROAD = 7;

    @Override
    protected short getFloorTerrain(int terrainIndex) {
        switch (terrainIndex) {
            case 0:
                return PROPERTY_DEFINITION_INDEX_GREEN_DUST;
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
                return PROPERTY_DEFINITION_INDEX_GREEN_STONE;
            case 1:
                return PROPERTY_DEFINITION_INDEX_WASTE_FUNGUS_TREE;
            case 2:
                return PROPERTY_DEFINITION_INDEX_GREEN_STONE_BRICK;
                default:
                    throw new IllegalArgumentException("Unhandled terrainIndex " + terrainIndex);
        }
    }

    @Override
    protected short getSpecialTerrain(int terrainIndex) {
        return PROPERTY_DEFINITION_INDEX_TAR_PAVED_ROAD;
    }

    @Override
    protected short getUntypedTerrain(char terrainSymbol) {
        return -1; //todo - shop entrances, probably
    }
}
