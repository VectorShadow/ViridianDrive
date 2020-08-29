package gamestate.gameobject.actor;

import definitions.ViridianDriveColors;
import frontend.UserPreferences;
import frontend.io.IOManager;
import frontend.io.Imageable;
import gamestate.gameobject.GameActor;
import images.ImageSource;
import images.TextImageSource;
import images.TrueImageSource;

import java.awt.*;

/**
 * Implementation of GameActor.
 */
public abstract class ViridianDriveActor extends GameActor implements Imageable {

    protected final int ARC_LENGTH = 9;

    protected static final double[] NO_VISION = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    //todo - implementation specific order fields, probably.

    private final TextImageSource ASCII_GFX = //hack - just one actor to display for now
            new TextImageSource(ViridianDriveColors.DISPLAY_BACKGROUND_0, Color.WHITE, '@');
    private final TrueImageSource IMAGE_GFX = null; //hack - null for now

    @Override
    public ImageSource getVisibleImageSource() {
        return !UserPreferences.getInstance().isGraphics() || IMAGE_GFX == null ? ASCII_GFX : IMAGE_GFX;
    }

    @Override
    public ImageSource getMemoryImageSource() {
        return null; //we don't remember actors, since they may move.
    }

    /**
     * @return a size 9 array of doubles, with values corresponding to the power of the vision arc that should be drawn
     * in the direction whose ordinal corresponds to the array index.
     */
    public abstract double[] getVisionArcs();

    /**
     * @return the default vision power of this actor under current optical conditions
     * Note that this should take into account time of day, weather, and status of optical instruments.
     */
    public abstract double getVisionPower();
}
