package gamestate.theme;

public abstract class TownTheme extends ViridianDriveTheme {

    private static final short PROPERTY_DEFINITION_INDEX_HANGAR = 2;
    private static final short PROPERTY_DEFINITION_INDEX_SALOON = 3;
    private static final short PROPERTY_DEFINITION_INDEX_WAREHOUSE = 4;
    private static final short PROPERTY_DEFINITION_INDEX_ARCTECH_1 = 5;
    private static final short PROPERTY_DEFINITION_INDEX_ARCTECH_2 = 6;
    private static final short PROPERTY_DEFINITION_INDEX_ARCTECH_3 = 7;
    private static final short PROPERTY_DEFINITION_INDEX_ARCTECH_4 = 8;
    private static final short PROPERTY_DEFINITION_INDEX_BLACKTAR_1 = 9;
    private static final short PROPERTY_DEFINITION_INDEX_BLACKTAR_2 = 10;
    private static final short PROPERTY_DEFINITION_INDEX_BLACKTAR_3 = 11;
    private static final short PROPERTY_DEFINITION_INDEX_BLACKTAR_4 = 12;
    private static final short PROPERTY_DEFINITION_INDEX_EONINFO_1 = 13;
    private static final short PROPERTY_DEFINITION_INDEX_EONINFO_2 = 14;
    private static final short PROPERTY_DEFINITION_INDEX_EONINFO_3 = 15;
    private static final short PROPERTY_DEFINITION_INDEX_EONINFO_4 = 16;
    private static final short PROPERTY_DEFINITION_INDEX_GOLDEN_1 = 17;
    private static final short PROPERTY_DEFINITION_INDEX_GOLDEN_2 = 18;
    private static final short PROPERTY_DEFINITION_INDEX_GOLDEN_3 = 19;
    private static final short PROPERTY_DEFINITION_INDEX_GOLDEN_4 = 20;
    private static final short PROPERTY_DEFINITION_INDEX_NIGHTMARE_1 = 21;
    private static final short PROPERTY_DEFINITION_INDEX_NIGHTMARE_2 = 22;
    private static final short PROPERTY_DEFINITION_INDEX_NIGHTMARE_3 = 23;
    private static final short PROPERTY_DEFINITION_INDEX_NIGHTMARE_4 = 24;
    private static final short PROPERTY_DEFINITION_INDEX_TITAN_1 = 25;
    private static final short PROPERTY_DEFINITION_INDEX_TITAN_2 = 26;
    private static final short PROPERTY_DEFINITION_INDEX_TITAN_3 = 27;
    private static final short PROPERTY_DEFINITION_INDEX_TITAN_4 = 28;
    private static final short PROPERTY_DEFINITION_INDEX_VITALIS_1 = 29;
    private static final short PROPERTY_DEFINITION_INDEX_VITALIS_2 = 30;
    private static final short PROPERTY_DEFINITION_INDEX_VITALIS_3 = 31;
    private static final short PROPERTY_DEFINITION_INDEX_VITALIS_4 = 32;

    @Override
    protected short getUntypedTerrain(char terrainSymbol) {
        switch (terrainSymbol) {
            case 'H': return PROPERTY_DEFINITION_INDEX_HANGAR;
            case 'S': return PROPERTY_DEFINITION_INDEX_SALOON;
            case 'W': return PROPERTY_DEFINITION_INDEX_WAREHOUSE;
            case 'a': return PROPERTY_DEFINITION_INDEX_ARCTECH_1;
            case 'A': return PROPERTY_DEFINITION_INDEX_ARCTECH_2;
            case 'r': return PROPERTY_DEFINITION_INDEX_ARCTECH_3;
            case 'R': return PROPERTY_DEFINITION_INDEX_ARCTECH_4;
            case 'b': return PROPERTY_DEFINITION_INDEX_BLACKTAR_1;
            case 'B': return PROPERTY_DEFINITION_INDEX_BLACKTAR_2;
            case 'l': return PROPERTY_DEFINITION_INDEX_BLACKTAR_3;
            case 'L': return PROPERTY_DEFINITION_INDEX_BLACKTAR_4;
            case 'e': return PROPERTY_DEFINITION_INDEX_EONINFO_1;
            case 'E': return PROPERTY_DEFINITION_INDEX_EONINFO_2;
            case 'i': return PROPERTY_DEFINITION_INDEX_EONINFO_3;
            case 'I': return PROPERTY_DEFINITION_INDEX_EONINFO_4;
            case 'g': return PROPERTY_DEFINITION_INDEX_GOLDEN_1;
            case 'G': return PROPERTY_DEFINITION_INDEX_GOLDEN_2;
            case 'f': return PROPERTY_DEFINITION_INDEX_GOLDEN_3;
            case 'F': return PROPERTY_DEFINITION_INDEX_GOLDEN_4;
            case 'n': return PROPERTY_DEFINITION_INDEX_NIGHTMARE_1;
            case 'N': return PROPERTY_DEFINITION_INDEX_NIGHTMARE_2;
            case 'm': return PROPERTY_DEFINITION_INDEX_NIGHTMARE_3;
            case 'M': return PROPERTY_DEFINITION_INDEX_NIGHTMARE_4;
            case 't': return PROPERTY_DEFINITION_INDEX_TITAN_1;
            case 'T': return PROPERTY_DEFINITION_INDEX_TITAN_2;
            case 'u': return PROPERTY_DEFINITION_INDEX_TITAN_3;
            case 'U': return PROPERTY_DEFINITION_INDEX_TITAN_4;
            case 'v': return PROPERTY_DEFINITION_INDEX_VITALIS_1;
            case 'V': return PROPERTY_DEFINITION_INDEX_VITALIS_2;
            case 'x': return PROPERTY_DEFINITION_INDEX_VITALIS_3;
            case 'X': return PROPERTY_DEFINITION_INDEX_VITALIS_4;
            default: throw new IllegalArgumentException("Unknown symbol: " + terrainSymbol);
        }
    }
}
