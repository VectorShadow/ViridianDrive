package frontend;

import definitions.*;
import frontend.io.GUIConstants;
import frontend.io.IOManager;
import frontend.io.inputcontext.LoginScreenInputContext;
import main.LiveLog;

public class LocalDriver {
    public static void main(String[] args) {
        LiveLog.setConsoleOutLevel(LiveLog.LogEntryPriority.INFO);
        DefinitionsManager.loadDefinitions(
                new ViridianDriveGameZoneGenerator(),
                new ViridianDriveGameZoneUpdateListener(),
                new LoginScreenInputContext(),
                new ViridianDriveOrderExecutor(),
                new ViridianDriveTerrainLookup()
        );
        IOManager.launchGui();
        IOManager.getGui().update(GUIConstants.CHANNEL_SPLASH_SCREEN);
    }
}
