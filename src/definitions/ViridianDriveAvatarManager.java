package definitions;

import user.AvatarClass;
import user.ViridianDriveAvatar;
import user.UserAvatar;

public class ViridianDriveAvatarManager extends AvatarManager {

    @Override
    public UserAvatar createNewAvatar(int creationParameters, String name) {
        //todo - parse creation parameters to determine class and other creation data
        return new ViridianDriveAvatar(AvatarClass.MERCENARY, name);
    }
}
