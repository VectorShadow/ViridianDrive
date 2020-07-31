package definitions;

import gamestate.terrain.TerrainProperties;
import gamestate.terrain.ViridianDriveTerrainProperties;

public class ViridianDriveTerrainLookup extends TerrainLookup {
    @Override
    protected TerrainProperties lookup(short terrainID) {
        switch (terrainID) {
            case 0:
                return ViridianDriveTerrainProperties.EMPTY_TILE;
            case 1:
                return ViridianDriveTerrainProperties.MAP_BOUNDARY;
                default:
                    throw new IllegalArgumentException("Unhandled ID: " + terrainID);
        }
    }
}