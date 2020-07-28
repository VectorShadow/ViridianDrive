package implementation.matrixupdater;

import images.ImageMatrix;
import images.TrueImageSource;

public class SplashScreenMatrixUpdater extends MatrixUpdater {

    private static final int SUPPORTED_LAYERS = 1;

    public SplashScreenMatrixUpdater() {
        super(SUPPORTED_LAYERS);
    }

    @Override
    protected ImageMatrix doUpdate() {
        ImageMatrix imageMatrix = LAYERS[currentLayer];
        imageMatrix.set(0,0, new TrueImageSource(0, 0));
        return imageMatrix;
    }
}
