package user;

import gamestate.coordinates.ZoneCoordinate;
import gamestate.gameobject.actor.DismountedPlayerActor;

public class ViridianDriveAvatar extends UserAvatar {

    public int[] classLevels = {0, 0, 0, 0, 0, 0};
    public int avatarLevel = 0;
    public final String NAME;

    public ViridianDriveAvatar(AvatarClass initialClass, String name) {
        avatarLevel = 1;
        classLevels[initialClass.ordinal()] = 1;
        NAME = name;
        setAt(ZoneCoordinate.ORIGIN_ZONE);
    }

    @Override
    AvatarMetadata buildMetadata() {
        return new ViridianDriveAvatarMetadata(at, classLevels, NAME);
    }

    //todo - we'll need to build or clone mecha and beasts here eventually from stored values, but for now,
    // simply create a new dismounted player avatar for wandering the town.
    @Override
    protected void createActor() {
        actor = new DismountedPlayerActor();
    }

}
