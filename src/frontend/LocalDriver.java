package frontend;

import backend.EngineManager;
import definitions.*;
import frontend.io.GUIConstants;
import frontend.io.IOManager;
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
                new ViridianDriveGameZoneUpdateListener(),
                new ViridianDriveOrderExecutor(),
                new ViridianDriveTerrainLookup()
        );
        IOManager.launchGui();
        IOManager.getGui().update(GUIConstants.CHANNEL_SPLASH_SCREEN);
        final boolean TEST_LOCAL = false;
        try {
            if (TEST_LOCAL)
                EngineManager.startLocalEngine();
            else
                EngineManager.connectToRemoteEngine();
        } catch (IOException e) {
            System.out.println("Exception on engine initialization:\n" + e);
        }
        try {
            Thread.sleep(500);
            //test login - account does not exist
            EngineManager.frontEndDataLink.transmit(new LogInRequestInstructionDatum("user", "pass"));
            Thread.sleep(500);
            //test creation
            EngineManager.frontEndDataLink.transmit(new AccountCreationRequestInstructionDatum("user", "pass"));
            Thread.sleep(500);
            //test duplicate login - incorrect password
            EngineManager.frontEndDataLink.transmit(new LogInRequestInstructionDatum("user", "wrongpass"));
            Thread.sleep(500);
            //test duplicate login - success
            EngineManager.frontEndDataLink.transmit(new LogInRequestInstructionDatum("user", "pass"));
            Thread.sleep(500);
            if (!TEST_LOCAL) {
                //test logout - invalid operation locally
                EngineManager.frontEndDataLink.transmit(new LogOutInstructionDatum("user"));
                Thread.sleep(500);
                //attempt to reconnect
                try {
                    EngineManager.connectToRemoteEngine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Thread.sleep(500);
            }
            //test login - incorrect password
            EngineManager.frontEndDataLink.transmit(new LogInRequestInstructionDatum("user", "wrongpass"));
            Thread.sleep(500);
            //test login - success
            EngineManager.frontEndDataLink.transmit(new LogInRequestInstructionDatum("user", "pass"));
            Thread.sleep(500);
            //test avatar selection
            PlayerSession.setPlayerAvatar(new DriveAvatar());
            EngineManager.frontEndDataLink.transmit(new SelectAvatarInstructionData(PlayerSession.getPlayerAvatar()));
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception during instruction testing:\n" + e);
        }
    }
}
