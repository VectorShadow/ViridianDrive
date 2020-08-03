package user;

import gamestate.coordinates.ZoneCoordinate;

public class DriveAvatar extends UserAvatar {
    public DriveAvatar() {
        setAt(ZoneCoordinate.ORIGIN_ZONE);
    }
}
