package definitions;

import frontend.io.GUIConstants;
import frontend.io.IOManager;

public class ViridianDriveGameZoneUpdateListener extends GameZoneUpdateListener {
    @Override
    public void changeGameZone() {
        IOManager.getGui().update(GUIConstants.CHANNEL_MAIN_GAME);
    }

    @Override
    public void updateGameZone() {
        IOManager.getGui().update(GUIConstants.CHANNEL_MAIN_GAME);
    }
}
