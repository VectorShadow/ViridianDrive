package frontend.io.inputcontext;

import frontend.io.GUIConstants;

import java.awt.event.KeyEvent;
import static frontend.io.IOManager.*;

public class SplashScreenInputContext extends InputContext {
    @Override
    protected void handleKeyPressed(KeyEvent e) {
        setInputContext(new LoginScreenInputContext());
        setOutputChannel(GUIConstants.CHANNEL_LOGIN);
    }

    @Override
    public void handleKeyReleased(KeyEvent e) {
        //nothing to do here
    }
}
