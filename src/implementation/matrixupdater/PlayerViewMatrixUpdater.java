package implementation.matrixupdater;

import images.ImageMatrix;
import images.TextImageSource;
import images.TrueImageSource;

import java.awt.*;

public class PlayerViewMatrixUpdater extends MatrixUpdater {
    private static final int SUPPORTED_LAYERS = 1;

    public PlayerViewMatrixUpdater() {
        super(SUPPORTED_LAYERS);
    }

    @Override
    protected ImageMatrix doUpdate() {
        ImageMatrix imageMatrix = LAYERS[currentLayer];
        for (int i = 0; i < imageMatrix.getMatrixHeight(); ++i) {
            for (int j = 0; j < imageMatrix.getMatrixWidth(); ++j) {
                imageMatrix.set(i, j, new TextImageSource(Color.BLACK, Color.WHITE, '.'));
            }
        }
        return imageMatrix;
    }
}
