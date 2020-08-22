package gamestate.theme.feature;

import gamestate.terrain.ViridianDriveTerrainFeature;

public class EmptyFeatureReader extends FeatureReader {
    @Override
    public ViridianDriveTerrainFeature readFeature(char featureSymbol) {
        return null;
    }
}
