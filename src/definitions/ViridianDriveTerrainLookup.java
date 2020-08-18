package definitions;

import gamestate.terrain.ViridianDriveTerrainProperties;

public class ViridianDriveTerrainLookup extends TerrainLookup {

    @Override
    protected ViridianDriveTerrainProperties lookup(short terrainID) {
        return ViridianDriveTerrainProperties.lookup(terrainID);
    }
}
