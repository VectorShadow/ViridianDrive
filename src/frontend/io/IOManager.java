package frontend.io;

import definitions.ViridianDriveColors;
import frontend.io.inputcontext.InputContext;
import frontend.io.inputcontext.SplashScreenInputContext;
import images.ImageSource;
import implementation.matrixupdater.*;
import implementation.paintinstructions.DefaultPaintInstruction;
import implementation.paintinstructions.TransparentBackgroundPaintInstruction;
import main.Gui;
import main.GuiBuilder;
import main.LiveLog;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static frontend.io.GUIConstants.*;

public class IOManager {

    public static Gui gui;

    private static InputContext inputContext = new SplashScreenInputContext();

    private static boolean graphicsMode = false;

    public static void launchGui() {
        ImageSource.setImageDirectoryPath("./gfx");
        gui = GuiBuilder
                .buildGui()
                .setSizeAndColor(CANVAS_HEIGHT, CANVAS_WIDTH, ViridianDriveColors.DISPLAY_BACKGROUND_0.getRGB())
                .setTitle("Viridian Drive [unversioned pre-alpha]")
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
                            CANVAS_HEIGHT,
                            CANVAS_WIDTH,
                            1,
                            1,
                            new SplashScreenMatrixUpdater(),
                            new DefaultPaintInstruction()
                    )
                /*
                 * OutputChannel 1 - Login Screen
                 */
                .addOutputChannel()
                    /*
                     * Channel 1 Region 0 - Login Options
                     */
                    .addRegion(
                            0,
                            0,
                            TEXT_TILE_0_HEIGHT,
                            TEXT_TILE_0_WIDTH,
                            FULL_TEXT_SCREEN_HEIGHT,
                            FULL_TEXT_SCREEN_WIDTH,
                            new LoginScreenMatrixUpdater(),
                            new DefaultPaintInstruction()
                    )
                /*
                 * OutputChannel 2 - Avatar Selection Channel
                 */
                .addOutputChannel()
                    /*
                     * Channel 2 Region 0 - Avatar Selection Options
                     */
                    .addRegion(
                            0,
                            0,
                            TEXT_TILE_0_HEIGHT,
                            TEXT_TILE_0_WIDTH,
                            FULL_TEXT_SCREEN_HEIGHT,
                            FULL_TEXT_SCREEN_WIDTH,
                            new AvatarSelectionScreenMatrixUpdater(),
                            new DefaultPaintInstruction()
                    )
                /*
                 * OutputChannel 3 - Main Game Play Channel
                 */
                .addOutputChannel()
                    /*
                     * Channel 3 Region 0 - Player Avatar View
                     */
                    .addRegion(
                            CH_MAIN_RG_VIEW_ORIGIN_X,
                            CH_MAIN_RG_VIEW_ORIGIN_Y,
                            CH_MAIN_RG_VIEW_TILE_DIMENSION,
                            CH_MAIN_RG_VIEW_TILE_DIMENSION,
                            CH_MAIN_RG_VIEW_HEIGHT,
                            CH_MAIN_RG_VIEW_WIDTH,
                            new PlayerViewMatrixUpdater(),
                            new DefaultPaintInstruction(),
                            new TransparentBackgroundPaintInstruction(),
                            new TransparentBackgroundPaintInstruction()
                    )
                    /*
                     * Channel 3 Region 1 - Location Description
                     */
                    .addRegion(
                            CH_MAIN_RG_LOCATION_DESCRIPTION_ORIGIN_X,
                            CH_MAIN_RG_LOCATION_DESCRIPTION_ORIGIN_Y,
                            TEXT_TILE_0_HEIGHT,
                            TEXT_TILE_0_WIDTH,
                            CH_MAIN_RG_LOCATION_DESCRIPTION_HEIGHT,
                            CH_MAIN_RG_LOCATION_DESCRIPTION_WIDTH,
                            new LocationDescriptionMatrixUpdater(),
                            new DefaultPaintInstruction()
                    )
                    /*
                     * Channel 3 Region 2 - Minimap
                     */
                    .addRegion(
                            CH_MAIN_RG_MINIMAP_ORIGIN_X,
                            CH_MAIN_RG_MINIMAP_ORIGIN_Y,
                            CH_MAIN_RG_MINIMAP_TILE_DIMENSION,
                            CH_MAIN_RG_MINIMAP_TILE_DIMENSION,
                            CH_MAIN_RG_MINIMAP_HEIGHT,
                            CH_MAIN_RG_MINIMAP_WIDTH,
                            new MinimapMatrixUpdater(),
                            new TransparentBackgroundPaintInstruction()
                    )
                    /*
                     * Channel 3 Region 3 - Compass
                     */
                    .addRegion(
                           CH_MAIN_RG_COMPASS_ORIGIN_X,
                           CH_MAIN_RG_COMPASS_ORIGIN_Y,
                           CH_MAIN_RG_COMPASS_TILE_DIMENSION,
                           CH_MAIN_RG_COMPASS_TILE_DIMENSION,
                           1,
                           1,
                           new CompassMatrixUpdater(),
                           new TransparentBackgroundPaintInstruction()
                    )
                    /*
                     * Channel 3 Region 4 - Micro View
                     */
                    .addRegion(
                            CH_MAIN_RG_MICRO_ORIGIN_X,
                            CH_MAIN_RG_MICRO_ORIGIN_Y,
                            CH_MAIN_RG_MICRO_TILE_DIMENSION,
                            CH_MAIN_RG_MICRO_TILE_DIMENSION,
                            CH_MAIN_RG_MICRO_HEIGHT,
                            CH_MAIN_RG_MICRO_WIDTH,
                            new MicroViewMatrixUpdater(),
                            new TransparentBackgroundPaintInstruction()
                    )
                    //todo - more regions?
                //todo - more channels
                .addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {

                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                inputContext.masterHandleKeyPress(e);
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                                inputContext.handleKeyReleased(e);
                            }
                        }
                )
                .addWindowListener(
                        new WindowListener() {
                            @Override
                            public void windowOpened(WindowEvent e) {

                            }

                            @Override
                            public void windowClosing(WindowEvent e) {

                            }

                            @Override
                            public void windowClosed(WindowEvent e) {
                                LiveLog.stop();
                                //todo - other shutdown procedures here
                            }

                            @Override
                            public void windowIconified(WindowEvent e) {

                            }

                            @Override
                            public void windowDeiconified(WindowEvent e) {

                            }

                            @Override
                            public void windowActivated(WindowEvent e) {

                            }

                            @Override
                            public void windowDeactivated(WindowEvent e) {

                            }
                        }
                )
                .build(30);
        //gui.toggleFullScreenMode();
    }

    public static boolean getGraphicsMode() {
        return graphicsMode;
    }

    public static void setGraphicsMode(boolean graphicsOn) {
        graphicsMode = graphicsOn;
    }

    public static void setInputContext(InputContext inputContext) {
        IOManager.inputContext = inputContext;
    }
    public static void setOutputChannel(int newChannel) {
        gui.setCurrentChannel(newChannel);
    }

    public static Gui getGui() {
        return gui;
    }
}
