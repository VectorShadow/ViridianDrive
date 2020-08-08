package frontend.io.inputcontext;

import backend.EngineManager;
import frontend.PlayerSession;
import frontend.io.GUIConstants;
import frontend.io.IOManager;
import link.instructions.AccountCreationRequestInstructionDatum;
import link.instructions.LogInRequestInstructionDatum;
import link.instructions.SelectAvatarInstructionData;
import main.LogHub;
import user.DriveAvatar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.awt.event.KeyEvent.*;

public class LoginScreenInputContext extends InputContext {
    private static Color statusColor = Color.GREEN;
    private static String statusMessage = "Play offline or enter a username and password to connect to the server.";

    private static boolean creatingNewAccount = false;
    private static boolean playingOnline = true;

    private static String savedPassword = "";

    private static int selectedIndex = 0;

    public static final String playOffline = "Play Offline";
    public static String enteredUsername = "";
    private static String enteredPassword = "";
    public static final String playOnline = "Login";
    public static final String quit = "Quit";

    private static final int OPTION_INDEX_PLAY_OFFLINE = 0;
    private static final int OPTION_INDEX_ENTER_USERNAME = 1;
    private static final int OPTION_INDEX_ENTER_PASSWORD = 2;
    private static final int OPTION_INDEX_PLAY_ONLINE = 3;
    private static final int OPTION_INDEX_QUIT = 4;

    public static String getHiddenPassword() {
        StringBuilder sb = new StringBuilder();
        for (char c : enteredPassword.toCharArray())
            sb.append('*');
        return sb.toString();
    }

    private static void go() {
        switch (selectedIndex) {
            case OPTION_INDEX_PLAY_OFFLINE:
                playingOnline = false;
                if (creatingNewAccount)
                    EngineManager.
                            frontEndDataLink.
                            transmit(
                                    new AccountCreationRequestInstructionDatum(
                                            "localUser",
                                            "defaultPassword"
                                    )
                            );
                else
                    EngineManager.
                            frontEndDataLink.
                            transmit(
                                    new LogInRequestInstructionDatum(
                                            "localUser",
                                            "defaultPassword"
                                    )
                            );
                break;
            case OPTION_INDEX_ENTER_USERNAME:
                selectedIndex = OPTION_INDEX_ENTER_PASSWORD;
                refreshScreen();
                break;
            case OPTION_INDEX_ENTER_PASSWORD: case OPTION_INDEX_PLAY_ONLINE:
                playingOnline = true;
                if (enteredUsername.length() < 4 || enteredUsername.length() > 16) {
                    statusColor = Color.YELLOW;
                    statusMessage = "Username must be at least 4 characters long and not more than 16.";
                    refreshScreen();
                } else if (enteredPassword.length() < 8 || enteredPassword.length() > 16) {
                    statusColor = Color.YELLOW;
                    statusMessage = "Password must be at least 8 characters long and not more than 16.";
                    refreshScreen();
                } else if (creatingNewAccount && !enteredPassword.equals(savedPassword)) {
                    statusColor = Color.YELLOW;
                    statusMessage = "Password confirmation failed. Try again.";
                    savedPassword = "";
                    enteredPassword = "";
                    refreshScreen();
                } else {
                    try {
                        EngineManager.connectToRemoteEngine();
                        if (creatingNewAccount)
                            EngineManager.
                                    frontEndDataLink.
                                    transmit(
                                            new AccountCreationRequestInstructionDatum(
                                                    enteredUsername,
                                                    enteredPassword
                                            )
                                    );
                        else
                            EngineManager.
                                    frontEndDataLink.
                                    transmit(
                                            new LogInRequestInstructionDatum(
                                                    enteredUsername,
                                                    enteredPassword
                                            )
                                    );
                    } catch (IOException e) {
                        statusColor = Color.RED;
                        statusMessage = "Unable to connect. Check your internet connection and try again, or play offline.";
                        refreshScreen();
                    }
                }
                break;
            case OPTION_INDEX_QUIT:
                System.exit(0);
        }
    }
    public static void loginResponseAccountAlreadyConnected() {
        if (playingOnline) {
            statusColor = Color.ORANGE;
            statusMessage = "The specified account is currently connected. " +
                    "Try again later, or select a different account.";
            refreshScreen();
        } else {
            LogHub.logFatalCrash("Fatal Error - Account Already Connected to local engine.",
                    new IllegalStateException());
        }
    }

    public static void loginResponseAccountDoesNotExist() {
        creatingNewAccount = true;
        if (playingOnline) {
            statusColor = Color.YELLOW;
            statusMessage = "Account does not yet exist. Re-enter password to confirm account creation.";
            savedPassword = enteredPassword;
            enteredPassword = "";
            refreshScreen();
        }
        else go(); //local account not yet created - simply try again using create rather than login
    }

    public static void loginResponseDuplicateAccountCreation() {
        LogHub.logFatalCrash("Fatal Error - Duplicate Account Creation detected.",
                new IllegalStateException());
    }

    public static void loginResponseIncorrectPassword() {
        if (playingOnline) {
            statusColor = Color.ORANGE;
            statusMessage = "Incorrect password entered. Re-enter the password or select a different account.";
            enteredPassword = "";
            selectedIndex = 2;
            refreshScreen();
        } else {
            LogHub.logFatalCrash("Fatal Error - Incorrect Password Login Response from local engine.",
                    new IllegalStateException());
        }
    }

    public static void loginResponseSuccess() {
        if (!playingOnline) EngineManager.startLocalEngine();
        //todo - go to an avatar selection screen here instead, but for now:
        PlayerSession.setPlayerAvatar(new DriveAvatar());
        EngineManager.frontEndDataLink.transmit(new SelectAvatarInstructionData(PlayerSession.getPlayerAvatar()));
        IOManager.setOutputChannel(GUIConstants.CHANNEL_MAIN_GAME);
        IOManager.setInputContext(new AvatarControlInputContext());
    }

    /**
     * Update the information used to draw this screen.
     * This must be called after any non-boolean state change in this class.
     */
    private static void refreshScreen() {
        IOManager.getGui().update(GUIConstants.CHANNEL_LOGIN);
    }

    @Override
    protected void handleKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int keyMod = e.getModifiersEx();
        switch (keyCode) {
            case VK_ENTER:
                go();
                break;
                //todo - more keycodes! move menu index, enter text, etc.
        }
    }

    @Override
    public void handleKeyReleased(KeyEvent e) {/*nothing to do here */}
}
