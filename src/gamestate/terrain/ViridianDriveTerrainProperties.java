package gamestate.terrain;

import frontend.io.IOManager;
import frontend.io.Imageable;
import images.ImageSource;
import images.TextImageSource;
import images.TrueImageSource;
import util.Direction;

import java.awt.*;

import static definitions.ViridianDriveColors.*;

//todo - shops as features, not terrain!
public class ViridianDriveTerrainProperties extends TerrainProperties implements Imageable {

    private static final ViridianDriveTerrainProperties[] TERRAIN_PROPERTY_DEFINITIONS = {
            // 0 - map boundary - used for all game zones
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_IMPASSABLE,
                    TerrainProperties.TRAVEL_PERMISSION_NONE,
                    new TextImageSource(Color.BLACK, Color.RED, '#'),
                    null
            ),
            // 1 - empty tile - used in TestGameZoneBuilder exclusively
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    TerrainProperties.TRAVEL_PERMISSION_NONE,
                    new TextImageSource(Color.BLACK, Color.WHITE,  '.'),
                    null
            ),
            // 2 - verdigris dust - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    TerrainProperties.TRAVEL_PERMISSION_NONE,
                    new TextImageSource(ASSET_VERDIGRIS_DUST, ASSET_BLUE_STONE, ' '),
                    null
            ),
            // 3 - waste flora - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_UNEVEN,
                    TerrainProperties.TRAVEL_PERMISSION_NONE,
                    new TextImageSource(ASSET_VERDIGRIS_DUST, ASSET_FUNGAL_FLORA_0, '*'),
                    null
            ),
            // 4 - blue stone - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_SKY,
                    TerrainProperties.TRAVEL_PERMISSION_NONE,
                    new TextImageSource(ASSET_VERDIGRIS_DUST, ASSET_BLUE_STONE, '#'),
                    null
            ),
            // 5 - waste fungus tree - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_OBSTACLE,
                    TerrainProperties.TRAVEL_PERMISSION_NONE,
                    new TextImageSource(ASSET_VERDIGRIS_DUST, ASSET_FUNGAL_FLORA_0, 'T'),
                    null
            ),
            // 6 - blue stone brick - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_SHEER,
                    TerrainProperties.TRAVEL_PERMISSION_NONE,
                    new TextImageSource(ASSET_BLUE_STONE, ASSET_BLUE_STONE_BRICK, '$'),
                    null
            ),
            // 7 - tar paved road - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    TerrainProperties.TRAVEL_PERMISSION_NONE,
                    new TextImageSource(ASSET_TAR_PAVEMENT, ASSET_VERDIGRIS_DUST, ':'),
                    null
            ),
            // 8 - arena entry gate - special - used to enter the Steadrock Settlement testing arena.
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    Direction.SOUTH.ordinal(),
                    new TextImageSource(ASSET_BLUE_STONE, ASSET_BLUE_STONE_BRICK, '0'),
                    null
            ),
            // 8 - arena exit gate - special - used to exit the Steadrock Settlement testing arena.
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    Direction.NORTH.ordinal(),
                    new TextImageSource(ASSET_BLUE_STONE, ASSET_BLUE_STONE_BRICK, '0'),
                    null
            )
    };

    private final TextImageSource ASCII_GFX;
    private final TrueImageSource IMAGE_GFX;

    public ViridianDriveTerrainProperties(
            int energyPermission,
            int matterPermission,
            int travelPermission,
            TextImageSource asciiGfx,
            TrueImageSource imageGfx
    ) {
        super(energyPermission, matterPermission, travelPermission);
        ASCII_GFX = asciiGfx;
        IMAGE_GFX = imageGfx;
    }

    @Override
    public ImageSource getVisibleImageSource() {
        //todo - apply ascii gfx coloration to image_gfx
        return !IOManager.getGraphicsMode() || IMAGE_GFX == null ? ASCII_GFX : IMAGE_GFX;
    }

    @Override
    public ImageSource getMemoryImageSource() {
        //todo - apply memory coloration to image_gfx
        return new TextImageSource(OVERRIDE_MEMORY_BACKGROUND, OVERRIDE_MEMORY_FOREGROUND, ASCII_GFX);
    }

    public static ViridianDriveTerrainProperties lookup(short terrainID) {
        if (terrainID >= TERRAIN_PROPERTY_DEFINITIONS.length)
            throw new IllegalArgumentException("Unhandled ID: " + terrainID);
        return TERRAIN_PROPERTY_DEFINITIONS[terrainID];
    }
}
