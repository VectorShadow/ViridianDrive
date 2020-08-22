package gamestate.theme.feature;

import gamestate.terrain.ViridianDriveTerrainFeature;

public abstract class FeatureReader {

    /**
     * Return a terrain feature corresponding to the specified feature symbol for this theme.
     */
    public abstract ViridianDriveTerrainFeature readFeature(char featureSymbol);
}
