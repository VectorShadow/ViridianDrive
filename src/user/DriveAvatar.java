package user;

import gamestate.coordinates.ZoneCoordinate;
import gamestate.gameobject.actor.DismountedPlayerActor;
import gamestate.gameobject.actor.TestActor;

public class DriveAvatar extends UserAvatar {

    public DriveAvatar() {
        setActor(new TestActor());
        setAt(ZoneCoordinate.ORIGIN_ZONE);
    }
}
