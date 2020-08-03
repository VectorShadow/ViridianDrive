package frontend;

import backend.EngineManager;
import definitions.DefinitionsManager;
import definitions.ViridianDriveGameZoneGenerator;
import definitions.ViridianDriveTerrainLookup;
import link.DataLink;
import link.instructions.AccountCreationRequestInstructionDatum;
import link.instructions.LogInRequestInstructionDatum;
import link.instructions.LogOutInstructionDatum;
import link.instructions.SelectAvatarInstructionData;
import main.LiveLog;
import user.DriveAvatar;

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
        final boolean TEST_LOCAL = false;
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
            if (!TEST_LOCAL) {
                //test logout - invalid operation locally
                frontend.transmit(new LogOutInstructionDatum("user"));
                Thread.sleep(500);
                //attempt to reconnect
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
            Thread.sleep(500);
            //test avatar selection
            frontend.transmit(new SelectAvatarInstructionData(new DriveAvatar()));
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception during instruction testing:\n" + e);
        }
    }
}
