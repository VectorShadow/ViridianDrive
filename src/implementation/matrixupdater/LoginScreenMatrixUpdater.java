package implementation.matrixupdater;

import frontend.io.IOManager;
import frontend.io.inputcontext.LoginScreenInputContext;
import images.ImageMatrix;
import images.TextImageSource;

import java.awt.*;

public class LoginScreenMatrixUpdater extends MatrixUpdater {

    private static final int SUPPORTED_LAYERS = 1;

    public LoginScreenMatrixUpdater() {
        super(SUPPORTED_LAYERS);
    }

    @Override
    protected ImageMatrix doUpdate() {
        ImageMatrix imageMatrix = LAYERS[currentLayer];
        writeLine(
                imageMatrix,
                2,
                3,
                LoginScreenInputContext.get().getStatusMessage(),
                IOManager.BG_RGB,
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
                    IOManager.BG_RGB, Color.WHITE
            );
        }
        return imageMatrix;
    }
    //todo - we should probably extract this to a more universally accessible location
    private void writeLine(ImageMatrix imageMatrix, int row, int startColumn, String line, Color background, Color foreground) {
        for (int i = 0; i < line.length(); ++i) {
            if (startColumn + i >= imageMatrix.getMatrixWidth()) return;
            imageMatrix.set(row, startColumn + i, new TextImageSource(background, foreground, line.charAt(i)));
        }
    }
}
