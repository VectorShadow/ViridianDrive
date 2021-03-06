package gamestate.terrain;

import frontend.UserPreferences;
import frontend.io.IOManager;
import frontend.io.Imageable;
import images.ImageSource;
import images.RecoloredTrueImageSource;
import images.TextImageSource;
import images.TrueImageSource;

import static definitions.ViridianDriveColors.*;
import static frontend.io.GUIConstants.*;

public enum ViridianDriveTerrainFeature implements Imageable, TerrainFeature {

    HANGAR(
            new TextImageSource(BUILDING_HANGAR_BG, BUILDING_HANGAR_FG, 'H'),
            new RecoloredTrueImageSource(
                    1,
                    TILE_GFX_FEATURE_ROW_0,
                    DISPLAY_BACKGROUND_0,
                    BUILDING_HANGAR_FG,
                    BUILDING_HANGAR_BG
            )
    ),
    SALOON(
            new TextImageSource(BUILDING_SALOON_BG, BUILDING_SALOON_FG, 'S'),
            null
    ),
    WAREHOUSE(
            new TextImageSource(BUILDING_WAREHOUSE_BG, BUILDING_WAREHOUSE_FG, 'W'),
            null
    ),
    ARCTECH1(
            new TextImageSource(CORPORATION_ARCTECH_BG, CORPORATION_ARCTECH_FG, '1'),
            null
    ),
    ARCTECH2(
            new TextImageSource(CORPORATION_ARCTECH_BG, CORPORATION_ARCTECH_FG, '2'),
            null
    ),
    ARCTECH3(
            new TextImageSource(CORPORATION_ARCTECH_BG, CORPORATION_ARCTECH_FG, '3'),
            null
    ),
    ARCTECH4(
            new TextImageSource(CORPORATION_ARCTECH_BG, CORPORATION_ARCTECH_FG, '4'),
            null
    ),
    BLACKTAR1(
            new TextImageSource(CORPORATION_BLACKTAR_BG, CORPORATION_BLACKTAR_FG, '1'),
            null
    ),
    BLACKTAR2(
            new TextImageSource(CORPORATION_BLACKTAR_BG, CORPORATION_BLACKTAR_FG, '2'),
            null
    ),
    BLACKTAR3(
            new TextImageSource(CORPORATION_BLACKTAR_BG, CORPORATION_BLACKTAR_FG, '3'),
            null
    ),
    BLACKTAR4(
            new TextImageSource(CORPORATION_BLACKTAR_BG, CORPORATION_BLACKTAR_FG, '4'),
            null
    ),
    EONINFO1(
            new TextImageSource(CORPORATION_EONINFO_BG, CORPORATION_EONINFO_FG, '1'),
            null
    ),
    EONINFO2(
            new TextImageSource(CORPORATION_EONINFO_BG, CORPORATION_EONINFO_FG, '2'),
            null
    ),
    EONINFO3(
            new TextImageSource(CORPORATION_EONINFO_BG, CORPORATION_EONINFO_FG, '3'),
            null
    ),
    EONINFO4(
            new TextImageSource(CORPORATION_EONINFO_BG, CORPORATION_EONINFO_FG, '4'),
            null
    ),
    GOLDEN1(
            new TextImageSource(CORPORATION_GOLDEN_BG, CORPORATION_GOLDEN_FG, '1'),
            null
    ),
    GOLDEN2(
            new TextImageSource(CORPORATION_GOLDEN_BG, CORPORATION_GOLDEN_FG, '2'),
            null
    ),
    GOLDEN3(
            new TextImageSource(CORPORATION_GOLDEN_BG, CORPORATION_GOLDEN_FG, '3'),
            null
    ),
    GOLDEN4(
            new TextImageSource(CORPORATION_GOLDEN_BG, CORPORATION_GOLDEN_FG, '4'),
            null
    ),
    NIGHTMARE1(
            new TextImageSource(CORPORATION_NIGHTMARE_BG, CORPORATION_NIGHTMARE_FG, '1'),
            null
    ),
    NIGHTMARE2(
            new TextImageSource(CORPORATION_NIGHTMARE_BG, CORPORATION_NIGHTMARE_FG, '2'),
            null
    ),
    NIGHTMARE3(
            new TextImageSource(CORPORATION_NIGHTMARE_BG, CORPORATION_NIGHTMARE_FG, '3'),
            null
    ),
    NIGHTMARE4(
            new TextImageSource(CORPORATION_NIGHTMARE_BG, CORPORATION_NIGHTMARE_FG, '4'),
            null
    ),
    TITAN1(
            new TextImageSource(CORPORATION_TITAN_BG, CORPORATION_TITAN_FG, '1'),
            null
    ),
    TITAN2(
            new TextImageSource(CORPORATION_TITAN_BG, CORPORATION_TITAN_FG, '2'),
            null
    ),
    TITAN3(
            new TextImageSource(CORPORATION_TITAN_BG, CORPORATION_TITAN_FG, '3'),
            null
    ),
    TITAN4(
            new TextImageSource(CORPORATION_TITAN_BG, CORPORATION_TITAN_FG, '4'),
            null
    ),
    VITALIS1(
            new TextImageSource(CORPORATION_VITALIS_BG, CORPORATION_VITALIS_FG, '1'),
            null
    ),
    VITALIS2(
            new TextImageSource(CORPORATION_VITALIS_BG, CORPORATION_VITALIS_FG, '2'),
            null
    ),
    VITALIS3(
            new TextImageSource(CORPORATION_VITALIS_BG, CORPORATION_VITALIS_FG, '3'),
            null
    ),
    VITALIS4(
            new TextImageSource(CORPORATION_VITALIS_BG, CORPORATION_VITALIS_FG, '4'),
            null
                    ),
    ;

    private final TextImageSource ASCII_GFX;
    private final TrueImageSource IMAGE_GFX;
    private final boolean HIDE_BY_DEFAULT;
    private final boolean AUTO_TRIGGERED;

    ViridianDriveTerrainFeature(TextImageSource asciiGfx, TrueImageSource imageGfx) {
        this(asciiGfx, imageGfx, false, false);
    }
    
    ViridianDriveTerrainFeature(
            TextImageSource asciiGfx,
            TrueImageSource imageGfx,
            boolean autoTrigger,
            boolean hideByDefault
    ) {
        ASCII_GFX = asciiGfx;
        IMAGE_GFX = imageGfx;
        AUTO_TRIGGERED = autoTrigger;
        HIDE_BY_DEFAULT = hideByDefault;
    }

    @Override
    public boolean isAutoTriggered() {
        return AUTO_TRIGGERED;
    }
    
    @Override
    public boolean isHidden() {
        return HIDE_BY_DEFAULT;
    }

    @Override
    public ImageSource getVisibleImageSource() {
        return !UserPreferences.getInstance().isGraphics() || IMAGE_GFX == null ? ASCII_GFX : IMAGE_GFX;
    }

    @Override
    public ImageSource getMemoryImageSource() {
        return !UserPreferences.getInstance().isGraphics() || IMAGE_GFX == null
                ? new TextImageSource(OVERRIDE_MEMORY_BACKGROUND, OVERRIDE_MEMORY_FOREGROUND, ASCII_GFX)
                : new RecoloredTrueImageSource(
                        IMAGE_GFX,
                        DISPLAY_BACKGROUND_0,
                        OVERRIDE_MEMORY_FOREGROUND,
                        OVERRIDE_MEMORY_BACKGROUND,
                        OVERRIDE_MEMORY_BACKGROUND
                    );
    }
}
