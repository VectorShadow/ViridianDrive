package frontend;

import backend.EngineManager;
import definitions.DefinitionsManager;
import definitions.ViridianDriveGameZoneGenerator;
import definitions.ViridianDriveTerrainLookup;
import link.DataLink;
import main.LiveLog;

public class LocalDriver {
    public static void main(String[] args) {
        LiveLog.setConsoleOutLevel(LiveLog.LogEntryPriority.INFO);
        DefinitionsManager.loadDefinitions(new ViridianDriveGameZoneGenerator(), new ViridianDriveTerrainLookup());
        GuiManager.launchGui();
        GuiManager.getGui().update();
        //todo - hack! start a local engine for now
        DataLink frontend = EngineManager.startLocalEngine();
    }
}
