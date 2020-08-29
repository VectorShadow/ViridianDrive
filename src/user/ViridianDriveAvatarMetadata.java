package user;

import gamestate.coordinates.ZoneCoordinate;

import java.awt.*;

public class ViridianDriveAvatarMetadata extends AvatarMetadata {
    private final AvatarClass AVATAR_CLASS;
    private final int LEVEL;
    private final String NAME;

    public ViridianDriveAvatarMetadata(ZoneCoordinate avatarLocation, int[] classLevels, String name) {
        super(avatarLocation);
        boolean[] hasClass = new boolean[classLevels.length];
        int totalLevels = 0;
        for (int i = 0; i < classLevels.length; ++i) {
            totalLevels += classLevels[i];
            hasClass[i] = classLevels[i] > 0;
        }
        AVATAR_CLASS = //todo - inaccurate and temporary
                hasClass[0]
                        ? AvatarClass.values()[0]
                        : hasClass[1]
                        ? AvatarClass.values()[1]
                        : hasClass[2]
                        ? AvatarClass.values()[2]
                        : hasClass[3]
                        ? AvatarClass.values()[3]
                        : hasClass[4]
                        ? AvatarClass.values()[4]
                        : AvatarClass.values()[5];
        LEVEL = totalLevels;
        NAME = name;
    }

    @Override
    public String toString() {
        return NAME + "(Level " + LEVEL + " " + AVATAR_CLASS + ")";
    }

    public Color getColor() {
        return AVATAR_CLASS.getColor();
    }
}
