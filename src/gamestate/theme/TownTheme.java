package gamestate.theme;

import gamestate.terrain.ViridianDriveTerrainFeature;

public abstract class TownTheme extends ViridianDriveTheme {

    @Override
    public ViridianDriveTerrainFeature readFeature(char terrainSymbol) {
        switch (terrainSymbol) {
            case 'H': return ViridianDriveTerrainFeature.HANGAR;
            case 'S': return ViridianDriveTerrainFeature.SALOON;
            case 'W': return ViridianDriveTerrainFeature.WAREHOUSE;
            case 'a': return ViridianDriveTerrainFeature.ARCTECH1;
            case 'A': return ViridianDriveTerrainFeature.ARCTECH2;
            case 'r': return ViridianDriveTerrainFeature.ARCTECH3;
            case 'R': return ViridianDriveTerrainFeature.ARCTECH4;
            case 'b': return ViridianDriveTerrainFeature.BLACKTAR1;
            case 'B': return ViridianDriveTerrainFeature.BLACKTAR2;
            case 'l': return ViridianDriveTerrainFeature.BLACKTAR3;
            case 'L': return ViridianDriveTerrainFeature.BLACKTAR4;
            case 'e': return ViridianDriveTerrainFeature.EONINFO1;
            case 'E': return ViridianDriveTerrainFeature.EONINFO2;
            case 'i': return ViridianDriveTerrainFeature.EONINFO3;
            case 'I': return ViridianDriveTerrainFeature.EONINFO4;
            case 'g': return ViridianDriveTerrainFeature.GOLDEN1;
            case 'G': return ViridianDriveTerrainFeature.GOLDEN2;
            case 'f': return ViridianDriveTerrainFeature.GOLDEN3;
            case 'F': return ViridianDriveTerrainFeature.GOLDEN4;
            case 'n': return ViridianDriveTerrainFeature.NIGHTMARE1;
            case 'N': return ViridianDriveTerrainFeature.NIGHTMARE2;
            case 'm': return ViridianDriveTerrainFeature.NIGHTMARE3;
            case 'M': return ViridianDriveTerrainFeature.NIGHTMARE4;
            case 't': return ViridianDriveTerrainFeature.TITAN1;
            case 'T': return ViridianDriveTerrainFeature.TITAN2;
            case 'u': return ViridianDriveTerrainFeature.TITAN3;
            case 'U': return ViridianDriveTerrainFeature.TITAN4;
            case 'v': return ViridianDriveTerrainFeature.VITALIS1;
            case 'V': return ViridianDriveTerrainFeature.VITALIS2;
            case 'x': return ViridianDriveTerrainFeature.VITALIS3;
            case 'X': return ViridianDriveTerrainFeature.VITALIS4;
            default: return null;
        }
    }
}
