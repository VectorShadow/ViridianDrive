package user;

import java.awt.*;

public class ViridianDriveAvatarMetadata extends AvatarMetadata {
    private final AvatarClass AVATAR_CLASS;
    private final int LEVEL;
    private final String NAME;

    public ViridianDriveAvatarMetadata(AvatarClass avatarClass, int level, String name) {
        AVATAR_CLASS = avatarClass;
        LEVEL = level;
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
