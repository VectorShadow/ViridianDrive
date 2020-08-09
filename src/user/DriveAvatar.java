package user;

import gamestate.coordinates.ZoneCoordinate;
import gamestate.gameobject.actor.DismountedPlayerActor;
import gamestate.gameobject.actor.DriveActor;

public class DriveAvatar extends UserAvatar {

    public DriveAvatar() {
        setAt(ZoneCoordinate.ORIGIN_ZONE);
    }

    //todo - we'll need to build or clone mecha and beasts here eventually from stored values, but for now,
    // simply create a new dismounted player avatar for wandering the town.
    @Override
    protected DriveActor deriveActor() {
        return new DismountedPlayerActor();
    }
}
