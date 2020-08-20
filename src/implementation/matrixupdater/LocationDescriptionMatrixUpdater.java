package implementation.matrixupdater;

import definitions.ViridianDriveGameZoneGenerator;
import gamestate.coordinates.ZoneCoordinate;
import images.ImageMatrix;
import user.PlayerSession;

import java.awt.*;

import static definitions.ViridianDriveColors.*;

public class LocationDescriptionMatrixUpdater extends MatrixUpdater {
    @Override
    protected ImageMatrix doUpdate(int currentLayer) {
        ImageMatrix imageMatrix = ImageMatrix.emptyCopy(layers[currentLayer]);
        if (PlayerSession.getCurrentAvatarIndex() >= 0) {
            ZoneCoordinate zoneCoordinate = PlayerSession.getCurrentAvatarMetadata().getZoneCoordinate();
            String locationDescription;
            Color textColor;
            switch (zoneCoordinate.LOCATION_ID) {
                case ViridianDriveGameZoneGenerator.WORLD_LOCATION_STEADROCK_SETTLEMENT:
                    locationDescription = "Steadrock Settlement";
                    textColor = LOCATION_DESCRIPTION_TOWN;
                    break;
                //todo - many more cases
                default:
                    throw new IllegalStateException("Unhandled location id " + zoneCoordinate.LOCATION_ID);
            }
            MatrixUpdaterTextOperations.writeLine(
                    imageMatrix,
                    0,
                    0,
                    locationDescription,
                    DISPLAY_BACKGROUND_0,
                    textColor
            );
        }
        return imageMatrix;
    }
}
