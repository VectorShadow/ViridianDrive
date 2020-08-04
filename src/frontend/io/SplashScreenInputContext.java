package frontend.io;

import java.awt.event.KeyEvent;
import static frontend.io.GuiManager.*;

public class SplashScreenInputContext extends InputContext {
    @Override
    public void handleKeyPress(KeyEvent e) {
        setInputContext(new AvatarControlInputContext());
        setOutputChannel(CH_MAIN);
    }
}
