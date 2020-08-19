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
            // 2 - Hangar entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(BUILDING_HANGAR_BG, BUILDING_HANGAR_FG, 'H'),
                    null
            ),
            // 3 - Saloon entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(BUILDING_SALOON_BG, BUILDING_SALOON_FG, 'S'),
                    null
            ),
            // 4 - Warehouse entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(BUILDING_WAREHOUSE_BG, BUILDING_WAREHOUSE_FG, 'W'),
                    null
            ),
            // 5 - Arctech Industries Tier 1 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_ARCTECH_BG, CORPORATION_ARCTECH_FG, '1'),
                    null
            ),
            // 6 - Arctech Industries Tier 2 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_ARCTECH_BG, CORPORATION_ARCTECH_FG, '2'),
                    null
            ),
            // 7 - Arctech Industries Tier 3 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_ARCTECH_BG, CORPORATION_ARCTECH_FG, '3'),
                    null
            ),
            // 8 - Arctech Industries Tier 4 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_ARCTECH_BG, CORPORATION_ARCTECH_FG, '4'),
                    null
            ),
            // 9 - Black Tar Energy Tier 1 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_BLACKTAR_BG, CORPORATION_BLACKTAR_FG, '1'),
                    null
            ),
            // 10 - Black Tar Energy Tier 2 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_BLACKTAR_BG, CORPORATION_BLACKTAR_FG, '2'),
                    null
            ),
            // 11 - Black Tar Energy Tier 3 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_BLACKTAR_BG, CORPORATION_BLACKTAR_FG, '3'),
                    null
            ),
            // 12 - Black Tar Energy Tier 4 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_BLACKTAR_BG, CORPORATION_BLACKTAR_FG, '4'),
                    null
            ),
            // 13 - Eon Information Systems Tier 1 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_EONINFO_BG, CORPORATION_EONINFO_FG, '1'),
                    null
            ),
            // 14 - Eon Information Systems Tier 2 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_EONINFO_BG, CORPORATION_EONINFO_FG, '2'),
                    null
            ),
            // 15 - Eon Information Systems Tier 3 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_EONINFO_BG, CORPORATION_EONINFO_FG, '3'),
                    null
            ),
            // 16 - Eon Information Systems Tier 4 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_EONINFO_BG, CORPORATION_EONINFO_FG, '4'),
                    null
            ),
            // 17 - Golden Financial Tier 1 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_GOLDEN_BG, CORPORATION_GOLDEN_FG, '1'),
                    null
            ),
            // 18 - Golden Financial Tier 2 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_GOLDEN_BG, CORPORATION_GOLDEN_FG, '2'),
                    null
            ),
            // 19 - Golden Financial Tier 3 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_GOLDEN_BG, CORPORATION_GOLDEN_FG, '3'),
                    null
            ),
            // 20 - Golden Financial Tier 4 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_GOLDEN_BG, CORPORATION_GOLDEN_FG, '4'),
                    null
            ),
            // 21 - Nightmare Labs Tier 1 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_NIGHTMARE_BG, CORPORATION_NIGHTMARE_FG, '1'),
                    null
            ),
            // 22 - Nightmare Labs Tier 2 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_NIGHTMARE_BG, CORPORATION_NIGHTMARE_FG, '2'),
                    null
            ),
            // 23 - Nightmare Labs Tier 3 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_NIGHTMARE_BG, CORPORATION_NIGHTMARE_FG, '3'),
                    null
            ),
            // 24 - Nightmare Labs Tier 4 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_NIGHTMARE_BG, CORPORATION_NIGHTMARE_FG, '4'),
                    null
            ),
            // 25 - Titan Technologies Tier 1 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_TITAN_BG, CORPORATION_TITAN_FG, '1'),
                    null
            ),
            // 26 - Titan Technologies Tier 2 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_TITAN_BG, CORPORATION_TITAN_FG, '2'),
                    null
            ),
            // 27 - Titan Technologies Tier 3 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_TITAN_BG, CORPORATION_TITAN_FG, '3'),
                    null
            ),
            // 28 - Titan Technologies Tier 4 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_TITAN_BG, CORPORATION_TITAN_FG, '4'),
                    null
            ),
            // 29 - Vitalis Tier 1 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_VITALIS_BG, CORPORATION_VITALIS_FG, '1'),
                    null
            ),
            // 30 - Vitalis Tier 2 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_VITALIS_BG, CORPORATION_VITALIS_FG, '2'),
                    null
            ),
            // 31 - Vitalis Tier 3 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_VITALIS_BG, CORPORATION_VITALIS_FG, '3'),
                    null
            ),
            // 32 - Vitalis Tier 4 Entrance - used in all towns
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(CORPORATION_VITALIS_BG, CORPORATION_VITALIS_FG, '4'),
                    null
            ),
            // 33 - verdigris dust - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(ASSET_VERDIGRIS_DUST, ASSET_BLUE_STONE, ' '),
                    null
            ),
            // 34 - waste flora - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_UNEVEN,
                    new TextImageSource(ASSET_VERDIGRIS_DUST, ASSET_FUNGAL_FLORA_0, '*'),
                    null
            ),
            // 35 - blue stone - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_SKY,
                    new TextImageSource(ASSET_VERDIGRIS_DUST, ASSET_BLUE_STONE, '#'),
                    null
            ),
            // 36 - waste fungus tree - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_OBSTACLE,
                    new TextImageSource(ASSET_VERDIGRIS_DUST, ASSET_FUNGAL_FLORA_0, 'T'),
                    null
            ),
            // 37 - green stone brick - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_SHEER,
                    new TextImageSource(ASSET_BLUE_STONE, ASSET_BLUE_STONE_BRICK, '$'),
                    null
            ),
            // 38 - tar paved road - used in VerdigrisWasteTownTheme
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE,
                    new TextImageSource(ASSET_TAR_PAVEMENT, ASSET_VERDIGRIS_DUST, ':'),
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
        return new TextImageSource(OVERRIDE_MEMORY_BACKGROUND, OVERRIDE_MEMORY_FOREGROUND, ASCII_GFX);
    }

    public static ViridianDriveTerrainProperties lookup(short terrainID) {
        if (terrainID >= TERRAIN_PROPERTY_DEFINITIONS.length)
            throw new IllegalArgumentException("Unhandled ID: " + terrainID);
        return TERRAIN_PROPERTY_DEFINITIONS[terrainID];
    }
}
