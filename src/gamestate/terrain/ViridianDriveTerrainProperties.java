package gamestate.terrain;

import frontend.io.IOManager;
import images.ImageSource;
import images.TextImageSource;
import images.TrueImageSource;

import java.awt.*;

import static definitions.ViridianDriveColors.*;

public class ViridianDriveTerrainProperties extends TerrainProperties {

    private static final ViridianDriveTerrainProperties[] TERRAIN_PROPERTY_DEFINITIONS = {
            // 0 - map boundary - used for all game zones
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_IMPASSABLE,
                    new TextImageSource(Color.BLACK, Color.RED, '#'),
                    null
            ),
            // 1 - empty tile - used in TestGameZoneBuilder exclusively
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(Color.BLACK, Color.WHITE,  '.'),
                    null
            ),
            // 2 - verdigris dust - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(VERDIGRIS_DUST, BLUE_STONE, ' '),
                    null
            ),
            //3 - waste flora - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_UNEVEN,
                    new TextImageSource(VERDIGRIS_DUST, FUNGAL_FLORA_0, '*'),
                    null
            ),
            //4 - blue stone - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_SKY,
                    new TextImageSource(VERDIGRIS_DUST, BLUE_STONE, '#'),
                    null
            ),
            //5 - waste fungus tree - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_OBSTACLE,
                    new TextImageSource(VERDIGRIS_DUST, FUNGAL_FLORA_0, 'T'),
                    null
            ),
            //6 - green stone brick - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_SHEER,
                    new TextImageSource(BLUE_STONE, BLUE_STONE_BRICK, '$'),
                    null
            ),
            //7 - tar paved road - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(TAR_PAVEMENT, VERDIGRIS_DUST, ':'),
                    null
            )

    };

    private final TextImageSource ASCII_GFX;
    private final TrueImageSource IMAGE_GFX;

    public ViridianDriveTerrainProperties(int energyPermission, int matterPermission, TextImageSource asciiGfx, TrueImageSource imageGfx) {
        super(energyPermission, matterPermission);
        ASCII_GFX = asciiGfx;
        IMAGE_GFX = imageGfx;
    }

    public ImageSource getVisibleImageSource() {
        //todo - apply ascii gfx coloration to image_gfx
        return !IOManager.getGraphicsMode() || IMAGE_GFX == null ? ASCII_GFX : IMAGE_GFX;
    }

    public ImageSource getMemoryImageSource() {
        //hack - get a memory colored version of the ascii image.
        //todo - apply memory coloration to image_gfx
        return new TextImageSource(MEMORY_BACKGROUND, MEMORY_FOREGROUND, ASCII_GFX);
    }

    public static ViridianDriveTerrainProperties lookup(short terrainID) {
        if (terrainID >= TERRAIN_PROPERTY_DEFINITIONS.length)
            throw new IllegalArgumentException("Unhandled ID: " + terrainID);
        return TERRAIN_PROPERTY_DEFINITIONS[terrainID];
    }
}
