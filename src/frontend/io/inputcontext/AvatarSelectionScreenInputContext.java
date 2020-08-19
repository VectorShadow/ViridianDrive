package frontend.io.inputcontext;

import backend.EngineManager;
import crypto.Password;
import frontend.io.GUIConstants;
import frontend.io.IOManager;
import link.instructions.SelectAvatarInstructionData;
import user.PlayerSession;
import user.UserAccount;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class AvatarSelectionScreenInputContext extends InputContext {

    public static int selectedIndex = 0;

    @Override
    protected void handleKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int keyMod = e.getModifiersEx();
        switch (keyCode) {
            case VK_ENTER:
                if (selectedIndex == countAvatars()) {
                    //for now, send a create avatar instruction
                    //todo - proceed to an avatar creation screen, use the result to transmit an accurate creation code
                    EngineManager.frontEndDataLink.transmit(new SelectAvatarInstructionData(-1));
                } else {
                    //select the avatar corresponding to the selected index
                    EngineManager.frontEndDataLink.transmit(new SelectAvatarInstructionData(selectedIndex));
                }
                IOManager.setInputContext(new AvatarControlInputContext());
                IOManager.setOutputChannel(GUIConstants.CHANNEL_MAIN_GAME);
                IOManager.getGui().update(GUIConstants.CHANNEL_MAIN_GAME);
                break;
            case VK_UP:
                if (--selectedIndex < 0)
                    selectedIndex =
                            countAvatars() >= UserAccount.MAX_AVATAR_COUNT //check the number of current avatars against the limit
                                    ? countAvatars() - 1 //don't let us select creation if we meet or exceed the limit
                                    : countAvatars();
                refreshScreen();
                break;
            case VK_DOWN:
                if (++selectedIndex > (countAvatars() >= UserAccount.MAX_AVATAR_COUNT ? countAvatars() - 1 : countAvatars()))
                    selectedIndex = 0;
                refreshScreen();
                break;
        }
    }

    private static int countAvatars() {
        return PlayerSession.getAccountMetadata().AVATAR_METADATA.size();
    }

    @Override
    public void handleKeyReleased(KeyEvent e) {
        //nothing to do here
    }

    @Override
    protected void refreshScreen() {
        IOManager.getGui().update(GUIConstants.CHANNEL_AVATAR_SELECTION);
    }
}
