package frontend.io.inputcontext;

import frontend.io.GUIConstants;
import frontend.io.IOManager;

import java.awt.event.KeyEvent;
import static frontend.io.IOManager.*;

public class SplashScreenInputContext extends InputContext {
    @Override
    protected void handleKeyPressed(KeyEvent e) {
        setInputContext(LoginScreenInputContext.get());
        setOutputChannel(GUIConstants.CHANNEL_LOGIN);
        IOManager.getGui().update(GUIConstants.CHANNEL_LOGIN);
    }

    @Override
    public void handleKeyReleased(KeyEvent e) {
        //nothing to do here
    }

    @Override
    protected void refreshScreen() {
        //nothing to do here
    }
}
