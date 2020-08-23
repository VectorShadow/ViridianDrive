package implementation.matrixupdater;

import gamestate.gameobject.MobileGameObject;
import images.ImageMatrix;
import images.RotatedTrueImageSource;
import user.PlayerSession;

public class CompassMatrixUpdater extends MatrixUpdater {
    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        RotatedTrueImageSource rtis =
                new RotatedTrueImageSource(
                        0,
                        0,
                        PlayerSession.getActor() == null ? MobileGameObject.FACING_NORTH : PlayerSession.getActor().getFacing()
                );
        imageMatrix.set(0, 0, rtis);
        return imageMatrix;
    }
}
