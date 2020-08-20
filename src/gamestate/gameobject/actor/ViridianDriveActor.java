package gamestate.gameobject.actor;

import definitions.ViridianDriveColors;
import frontend.io.IOManager;
import frontend.io.Imageable;
import gamestate.gameobject.GameActor;
import images.ImageSource;
import images.TextImageSource;
import images.TrueImageSource;

import java.awt.*;

import static definitions.ViridianDriveColors.OVERRIDE_MEMORY_BACKGROUND;
import static definitions.ViridianDriveColors.OVERRIDE_MEMORY_FOREGROUND;

/**
 * Implementation of GameActor.
 */
public abstract class ViridianDriveActor extends GameActor implements Imageable {

    //todo - implementation specific order fields, probably.

    private final TextImageSource ASCII_GFX = //hack - just one actor to display for now
            new TextImageSource(ViridianDriveColors.DISPLAY_BACKGROUND_0, Color.WHITE, '@');
    private final TrueImageSource IMAGE_GFX = null; //hack - null for now

    @Override
    public ImageSource getVisibleImageSource() {
        //todo - apply ascii gfx coloration to image_gfx
        return !IOManager.getGraphicsMode() || IMAGE_GFX == null ? ASCII_GFX : IMAGE_GFX;
    }

    @Override
    public ImageSource getMemoryImageSource() {
        return null; //we don't remember actors, since they may move.
    }
}
