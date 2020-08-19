package implementation.matrixupdater;

import frontend.io.inputcontext.LoginScreenInputContext;
import images.ImageMatrix;

import static definitions.ViridianDriveColors.*;
import static implementation.matrixupdater.MatrixUpdaterTextOperations.*;

public class LoginScreenMatrixUpdater extends MatrixUpdater {

    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        writeLine(
                imageMatrix,
                2,
                3,
                LoginScreenInputContext.get().getStatusMessage(),
                DISPLAY_BACKGROUND_0,
                LoginScreenInputContext.get().getStatusColor()
        );
        for (int row = 0; row < LoginScreenInputContext.OPTION_COUNT; ++row) {
            writeLine(
                    imageMatrix,
                    row + 4,
                    row == LoginScreenInputContext.get().getSelectedIndex() ? 1 : 3,
                    row == LoginScreenInputContext.get().getSelectedIndex() ?
                            "> " + LoginScreenInputContext.get().getText(row)
                            : LoginScreenInputContext.get().getText(row),
                    DISPLAY_BACKGROUND_0, DISPLAY_FOREGROUND_0
            );
        }
        return imageMatrix;
    }
}
