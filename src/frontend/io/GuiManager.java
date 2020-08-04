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

public class GuiManager {

    public static final Color BG_RGB = Color.BLACK;

    public static final int CH_SPLASH = 0;
    public static final int CH_SPLASH_RG_ART = 0;

    //todo - more channels here - menus, etc.

    public static final int CH_MAIN = 1;
    public static final int CH_MAIN_RG_VIEW = 0;

    //todo - more channels here - inventory, corporation, etc., probably

    public static Gui gui;

    private static InputContext inputContext = new SplashScreenInputContext();

    public static void launchGui() {
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
                /*
                 * OutputChannel 1 - Main Game Play Channel
                 */
                .addOutputChannel()
                    /*
                     * Channel 1 Region 0 - Player Avatar View
                     */
                    .addRegion(96,
                            48,
                            24,
                            24,
                            25,
                            32,
                            new PlayerViewMatrixUpdater(),
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
        gui.toggleFullScreenMode();
    }

    public static void setInputContext(InputContext inputContext) {
        GuiManager.inputContext = inputContext;
    }
    public static void setOutputChannel(int newChannel) {
        gui.setCurrentChannel(newChannel);
    }

    public static Gui getGui() {
        return gui;
    }
}
