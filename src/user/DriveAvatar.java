package user;

import gamestate.coordinates.ZoneCoordinate;
import gamestate.gameobject.actor.DismountedPlayerActor;

public class DriveAvatar extends UserAvatar {

    public DriveAvatar() {
        setActor(new DismountedPlayerActor());
        setAt(ZoneCoordinate.ORIGIN_ZONE);
    }
}
