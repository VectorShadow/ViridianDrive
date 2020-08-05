package frontend;

import gamestate.gameobject.actor.DriveActor;
import user.DriveAvatar;
import user.UserAccount;

/**
 * Access UserAccount's active session to provide implementation specific data to IOManager.
 */
public class PlayerSession {

    public static DriveActor getPlayerActor() {
        return (DriveActor) getPlayerAvatar().getActor();
    }

    public static DriveAvatar getPlayerAvatar() {
        return (DriveAvatar) UserAccount.activeSession.getCurrentAvatar();
    }

    public static void setPlayerActor(DriveActor driveActor) {
        getPlayerAvatar().setActor(driveActor);
    }

    public static void setPlayerAvatar(DriveAvatar driveAvatar) {
        UserAccount.activeSession.setCurrentAvatar(driveAvatar);
    }
}
