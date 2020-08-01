package frontend;

import backend.EngineManager;
import definitions.DefinitionsManager;
import definitions.ViridianDriveGameZoneGenerator;
import definitions.ViridianDriveTerrainLookup;
import link.DataLink;
import link.instructions.AccountCreationRequestInstructionDatum;
import link.instructions.LoginRequestInstructionDatum;
import main.LiveLog;

import java.io.IOException;

public class LocalDriver {
    public static void main(String[] args) {
        LiveLog.setConsoleOutLevel(LiveLog.LogEntryPriority.INFO);
        DefinitionsManager.loadDefinitions(
                new ViridianDriveGameZoneGenerator(),
                new ViridianDriveTerrainLookup()
        );
        GuiManager.launchGui();
        GuiManager.getGui().update();
        //todo - hack! start a local engine for now
        final boolean TEST_LOCAL = true;
        DataLink frontend = null;
        try {
            if (TEST_LOCAL)
                frontend = EngineManager.startLocalEngine();
            else
                frontend = EngineManager.connectToRemoteEngine();
        } catch (IOException e) {
            System.out.println("Exception on engine initialization:\n" + e);
        }
        try {
            Thread.sleep(500);
            //test login - account does not exist
            frontend.transmit(new LoginRequestInstructionDatum("user", "pass"));
            Thread.sleep(500);
            //test creation
            frontend.transmit(new AccountCreationRequestInstructionDatum("user", "pass"));
            Thread.sleep(500);
            //test login - incorrect password
            frontend.transmit(new LoginRequestInstructionDatum("user", "wrongpass"));
            Thread.sleep(500);
            //test login - success
            frontend.transmit(new LoginRequestInstructionDatum("user", "pass"));
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception during instruction testing:\n" + e);
        }
    }
}
