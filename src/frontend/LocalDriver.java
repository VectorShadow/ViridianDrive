package frontend;

import backend.EngineManager;
import definitions.DefinitionsManager;
import definitions.ViridianDriveGameZoneGenerator;
import definitions.ViridianDriveTerrainLookup;
import link.DataLink;
import link.instructions.AccountCreationRequestInstructionDatum;
import link.instructions.LogInRequestInstructionDatum;
import link.instructions.LogOutRequestInstructionDatum;
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
            frontend.transmit(new LogInRequestInstructionDatum("user", "pass"));
            Thread.sleep(500);
            //test creation
            frontend.transmit(new AccountCreationRequestInstructionDatum("user", "pass"));
            Thread.sleep(500);
            //test duplicate login - incorrect password
            frontend.transmit(new LogInRequestInstructionDatum("user", "wrongpass"));
            Thread.sleep(500);
            //test duplicate login - success
            frontend.transmit(new LogInRequestInstructionDatum("user", "pass"));
            Thread.sleep(500);
            //test logout
            frontend.transmit(new LogOutRequestInstructionDatum("user"));
            Thread.sleep(500);
            if (!TEST_LOCAL) {
                //logout resets engine tracking of the user accounts and causes the datalink to be purged.
                // locally, the paired datalinks remain connected, but remotely, the socket connection is broken when
                // the original data link is purged, so we need to reconnect to continue testing.
                try {
                    frontend = EngineManager.connectToRemoteEngine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Thread.sleep(500);
            }
            //test login - incorrect password
            frontend.transmit(new LogInRequestInstructionDatum("user", "wrongpass"));
            Thread.sleep(500);
            //test login - success
            frontend.transmit(new LogInRequestInstructionDatum("user", "pass"));
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception during instruction testing:\n" + e);
        }
    }
}
