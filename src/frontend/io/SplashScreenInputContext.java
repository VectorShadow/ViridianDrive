package frontend.io;

import java.awt.event.KeyEvent;
import static frontend.io.IOManager.*;

public class SplashScreenInputContext extends InputContext {
    @Override
    public void handleKeyPressed(KeyEvent e) {
        setInputContext(new AvatarControlInputContext());
        setOutputChannel(GUIConstants.CHANNEL_MAIN_GAME);
    }

    @Override
    protected void handleKeyReleased(KeyEvent e) {
        //nothing to do here
    }
}
