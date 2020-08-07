package gamestate.terrain;

import frontend.io.IOManager;
import images.ImageSource;
import images.TextImageSource;
import images.TrueImageSource;

import java.awt.*;

public class ViridianDriveTerrainProperties extends TerrainProperties {

    public static final ViridianDriveTerrainProperties EMPTY_TILE =
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(Color.BLACK, Color.WHITE,  '.'),
                    null
            );
    public static final ViridianDriveTerrainProperties MAP_BOUNDARY =
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_IMPASSABLE,
                    new TextImageSource(Color.BLACK, Color.RED, '#'),
                    null
            );

    private final TextImageSource ASCII_GFX;
    private final TrueImageSource IMAGE_GFX;

    public ViridianDriveTerrainProperties(int energyPermission, int matterPermission, TextImageSource asciiGfx, TrueImageSource imageGfx) {
        super(energyPermission, matterPermission);
        ASCII_GFX = asciiGfx;
        IMAGE_GFX = imageGfx;
    }

    public ImageSource getImageSource() {
        return !IOManager.getGraphicsMode() || IMAGE_GFX == null ? ASCII_GFX : IMAGE_GFX;
    }
}
