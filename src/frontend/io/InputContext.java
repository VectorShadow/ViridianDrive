package frontend.io;

import java.awt.event.KeyEvent;
import static frontend.io.GuiManager.*;

/**
 * Determine how the frontend handles inputs.
 */
public abstract class InputContext {

    void masterHandleKeyPress(KeyEvent e) {
        //handle events which produce the same result in any context
        if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiersEx() == KeyEvent.ALT_DOWN_MASK)
            gui.toggleFullScreenMode();
        //todo - additional master event handling?
        //otherwise delegate to the implementation
        else
            handleKeyPress(e);
    }

    protected abstract void handleKeyPress(KeyEvent e);
}
