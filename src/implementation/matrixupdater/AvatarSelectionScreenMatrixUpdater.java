package implementation.matrixupdater;

import frontend.io.inputcontext.AvatarSelectionScreenInputContext;
import images.ImageMatrix;
import user.AvatarMetadata;
import user.PlayerSession;
import user.UserAccount;
import user.ViridianDriveAvatarMetadata;

import static definitions.ViridianDriveColors.*;
import static implementation.matrixupdater.MatrixUpdaterTextOperations.*;

public class AvatarSelectionScreenMatrixUpdater extends MatrixUpdater {
    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        int row = 2;
        int startColumn = 3;
        writeLine(
                imageMatrix,
                row,
                startColumn,
                "Select an Avatar to play as:",
                DISPLAY_BACKGROUND_0,
                DISPLAY_FOREGROUND_0
        );
        row += 2;
        for (AvatarMetadata avatarMetadata : PlayerSession.getAccountMetadata().AVATAR_METADATA) {
            writeLine(
                    imageMatrix,
                    row++,
                    startColumn + 1,
                    avatarMetadata.toString(),
                    DISPLAY_BACKGROUND_0,
                    ((ViridianDriveAvatarMetadata)avatarMetadata).getColor()
            );
        }
        writeLine(
                imageMatrix,
                row++,
                startColumn + 1,
                PlayerSession.getAccountMetadata().AVATAR_METADATA.size() >= UserAccount.MAX_AVATAR_COUNT
                        ? "Avatar Limit Reached"
                        : "Create New Avatar",
                DISPLAY_BACKGROUND_0,
                PlayerSession.getAccountMetadata().AVATAR_METADATA.size() >= UserAccount.MAX_AVATAR_COUNT
                        ? STATUS_ERROR
                        : STATUS_PENDING
        );
        writeLine(
                imageMatrix,
                AvatarSelectionScreenInputContext.selectedIndex + 4,
                startColumn - 1,
                ">",
                DISPLAY_BACKGROUND_0,
                DISPLAY_FOREGROUND_0
        );
        //todo
        return imageMatrix;
    }
}
