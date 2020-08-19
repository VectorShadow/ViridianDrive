package implementation.matrixupdater;

import images.ImageMatrix;
import images.TrueImageSource;

public class SplashScreenMatrixUpdater extends MatrixUpdater {

    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        imageMatrix.set(0,0, new TrueImageSource(0, 0));
        return imageMatrix;
    }
}
