package definitions;

import user.DriveAvatar;
import user.UserAvatar;

public class ViridianDriveAvatarManager extends AvatarManager {
    @Override
    public UserAvatar createNewAvatar(int creationParameters) {
        //todo - creation paramaters should be of the form 0x80abcdef - use these to create the Avatar
        return new DriveAvatar();
    }
}
