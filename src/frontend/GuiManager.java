package frontend;

import images.Renderer;
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
                .addKeyListener(
                        new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {

                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                handleKeyPress(e);
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
        //gui.toggleFullScreenMode();
    }

    public static Gui getGui() {
        return gui;
    }


    public static void handleKeyPress(KeyEvent e) {
        //todo - lots more here. This will probably want its own class or suite of classes eventually.
        if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiersEx() == KeyEvent.ALT_DOWN_MASK)
            gui.toggleFullScreenMode();
    }
}
