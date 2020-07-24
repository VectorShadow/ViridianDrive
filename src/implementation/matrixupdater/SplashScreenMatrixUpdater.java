package implementation.matrixupdater;

import images.ImageMatrix;
import images.TrueImageSource;

import java.awt.*;

public class SplashScreenMatrixUpdater extends MatrixUpdater {

    private static final int SUPPORTED_LAYERS = 1;

    private static final Color AZURE = new Color(63, 159, 255);
    private static final Color VIRIDIAN = new Color(80, 160, 48);

    public SplashScreenMatrixUpdater() {
        super(SUPPORTED_LAYERS);
    }

    @Override
    protected ImageMatrix doUpdate() {
        ImageMatrix imageMatrix = LAYERS[currentLayer];
        imageMatrix.set(0,0, new TrueImageSource(imageMatrix.getImageHeight(), imageMatrix.getImageWidth()));
        return imageMatrix;
    }
}
