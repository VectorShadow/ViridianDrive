package gamestate.theme;

import gamestate.terrain.ViridianDriveTerrainProperties;

public class GreenWasteTownTheme extends ViridianDriveTheme {
    //todo - lots here!
    @Override
    public ViridianDriveTerrainProperties getFloorTerrain(int terrainIndex) {
        return null;
    }

    @Override
    public ViridianDriveTerrainProperties getWallTerrain(int terrainIndex) {
        return null;
    }

    @Override
    public ViridianDriveTerrainProperties getSpecialTerrain(int terrainIndex) {
        return null;
    }

    @Override
    public ViridianDriveTerrainProperties getUntypedTerrain(char terrainSymbol) {
        return null;
    }
}
