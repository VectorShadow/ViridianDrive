package frontend.io;

import images.ImageSource;

/**
 * All Game Assets which might need to be drawn to the player view must implement this interface.
 */
public interface Imageable {
    ImageSource getVisibleImageSource();
    ImageSource getMemoryImageSource();
}
