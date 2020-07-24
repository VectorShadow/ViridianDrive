package frontend;

import images.Renderer;
import implementation.matrixupdater.SplashScreenMatrixUpdater;
import implementation.paintinstructions.DefaultPaintInstruction;
import main.Gui;
import main.GuiBuilder;

import java.awt.*;

public class GuiManager {

    public static final Color BG_RGB = Color.BLACK;

    private static final int CH_SPLASH = 0;
    //todo - more channels
    private static final int CH_SPLASH_RG_ART = 0;

    private static Gui gui;

    static void launchGui() {
        Renderer.setImageDirectoryPath("./gfx");
        gui = GuiBuilder
                .buildGui()
                .setSizeAndColor(864, 1280, BG_RGB.getRGB())
                /*
                 * OutputChannel 0 - Splash Screen
                 */
                .addOutputChannel()
                    /*
                     * Channel 0 Region 0 - Splash Art
                     */
                    .addRegion(
                            0,
                            0,
                            864,
                            1280,
                            1,
                            1,
                            new SplashScreenMatrixUpdater(),
                            new DefaultPaintInstruction()
                    )
                    //todo - more regions?
                //todo - more channels
                .build(30);
        //gui.toggleFullScreenMode();
    }

    public static Gui getGui() {
        return gui;
    }
}
