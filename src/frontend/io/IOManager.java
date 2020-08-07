package frontend.io;

import images.Renderer;
import implementation.matrixupdater.PlayerViewMatrixUpdater;
import implementation.matrixupdater.SplashScreenMatrixUpdater;
import implementation.paintinstructions.DefaultPaintInstruction;
import main.Gui;
import main.GuiBuilder;
import main.LiveLog;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static frontend.io.GUIConstants.*;

public class IOManager {

    public static final Color BG_RGB = Color.BLACK;

    public static Gui gui;

    private static InputContext inputContext = new SplashScreenInputContext();

    private static boolean graphicsMode = false;

    public static void launchGui() {
        Renderer.setImageDirectoryPath("./gfx");
        gui = GuiBuilder
                .buildGui()
                .setSizeAndColor(CANVAS_HEIGHT, CANVAS_WIDTH, BG_RGB.getRGB())
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
                 * OutputChannel 1 - Main Game Play Channel
                 */
                .addOutputChannel()
                    /*
                     * Channel 1 Region 0 - Player Avatar View
                     */
                    .addRegion(REGION_VIEW_ORIGIN_X,
                            REGION_VIEW_ORIGIN_Y,
                            REGION_VIEW_TILE_DIMENSION,
                            REGION_VIEW_TILE_DIMENSION,
                            REGION_VIEW_HEIGHT,
                            REGION_VIEW_WIDTH,
                            new PlayerViewMatrixUpdater(),
                            new DefaultPaintInstruction(),
                            new DefaultPaintInstruction()
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
