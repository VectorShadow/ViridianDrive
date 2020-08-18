package gamestate.terrain;

import frontend.io.IOManager;
import images.ImageSource;
import images.TextImageSource;
import images.TrueImageSource;

import java.awt.*;

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
            // 2 - green dust - used in GreenWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(Color.BLACK, Color.GREEN, '.'),
                    null
            ),
            //3 - waste flora - used in GreenWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_UNEVEN,
                    new TextImageSource(Color.BLACK, Color.PINK, '*'),
                    null
            ),
            //4 - green stone - used in GreenWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_SKY,
                    new TextImageSource(Color.BLACK, Color.GREEN, '#'),
                    null
            ),
            //5 - waste fungus tree - used in GreenWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_OBSTACLE,
                    new TextImageSource(Color.BLACK, Color.PINK, 'T'),
                    null
            ),
            //6 - green stone brick - used in GreenWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_SHEER,
                    new TextImageSource(Color.BLACK, Color.GREEN, '$'),
                    null
            ),
            //7 - tar paved road - used in GreenWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(Color.BLACK, Color.DARK_GRAY, ':'),
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

    public ImageSource getImageSource() {
        return !IOManager.getGraphicsMode() || IMAGE_GFX == null ? ASCII_GFX : IMAGE_GFX;
    }

    public static ViridianDriveTerrainProperties lookup(short terrainID) {
        if (terrainID >= TERRAIN_PROPERTY_DEFINITIONS.length)
            throw new IllegalArgumentException("Unhandled ID: " + terrainID);
        return TERRAIN_PROPERTY_DEFINITIONS[terrainID];
    }
}
