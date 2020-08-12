package frontend.io.inputcontext;

import backend.EngineManager;
import crypto.Password;
import definitions.DefinitionsManager;
import definitions.LoginResponseHandler;
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

public class LoginScreenInputContext extends InputContext implements LoginResponseHandler {

    private static final int USERNAME_LENGTH_MAX = 16;
    private static final int USERNAME_LENGTH_MIN = 4;

    private Color statusColor = Color.GREEN;
    private String statusMessage = "Play Offline, or login to play Online.";

    private boolean creatingNewAccount = false;
    private boolean playingOnline = true;

    private String savedPassword = "";

    private int selectedIndex = 0;

    private String enteredUsername = "";
    private String enteredPassword = "";

    private final int OPTION_INDEX_ENTER_USERNAME = 0;
    private final int OPTION_INDEX_ENTER_PASSWORD = 1;
    private final int OPTION_INDEX_PLAY_OFFLINE = 2;
    private final int OPTION_INDEX_QUIT = 3;

    public static final int OPTION_COUNT = 4;

    public String getText(int fieldIndex) {
        switch (fieldIndex) {
            case OPTION_INDEX_ENTER_USERNAME:
                return "Username: " + enteredUsername;
            case OPTION_INDEX_ENTER_PASSWORD:
                StringBuilder sb = new StringBuilder("Password: ");
                for (char c : enteredPassword.toCharArray())
                    sb.append('*');
                return sb.toString();
            case OPTION_INDEX_PLAY_OFFLINE:
                return "Play Offline";
            case OPTION_INDEX_QUIT:
                return "Quit";
                default:
                    throw new IllegalArgumentException("Unsupported field index: " + fieldIndex);
        }
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public Color getStatusColor() {
        return statusColor;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    private void go() {
        switch (selectedIndex) {
            case OPTION_INDEX_ENTER_USERNAME:
                selectedIndex = OPTION_INDEX_ENTER_PASSWORD;
                refreshScreen();
                break;
            case OPTION_INDEX_ENTER_PASSWORD:
                playingOnline = true;
                if (enteredUsername.length() < USERNAME_LENGTH_MIN || enteredUsername.length() > USERNAME_LENGTH_MAX) {
                    statusColor = Color.YELLOW;
                    statusMessage = "Username must be " + USERNAME_LENGTH_MIN +
                            " - " + USERNAME_LENGTH_MAX + "characters.";
                    refreshScreen();
                } else if (
                        enteredPassword.length() < Password.MINIMUM_LENGTH ||
                                enteredPassword.length() > Password.MAXIMUM_LENGTH
                ) {
                    statusColor = Color.YELLOW;
                    statusMessage = "Password must be "+ Password.MINIMUM_LENGTH +
                            " - " + Password.MAXIMUM_LENGTH + "characters.";
                    refreshScreen();
                } else if (creatingNewAccount && !enteredPassword.equals(savedPassword)) {
                    statusColor = Color.YELLOW;
                    statusMessage = "Password confirmation failed. Try again.";
                    creatingNewAccount = false;
                    savedPassword = "";
                    enteredPassword = "";
                    refreshScreen();
                } else {
                    try {
                        EngineManager.connectToRemoteEngine();
                        statusColor = Color.BLUE;
                        statusMessage = "Connecting...";
                        refreshScreen();
                        do {
                            Thread.sleep(250); //wait for crypto handshake
                        } while (!EngineManager.frontEndDataLink.isEncrypted());
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
                        statusMessage = "Unable to connect. Try again or play offline.";
                        refreshScreen();
                    } catch (InterruptedException e) {
                        LogHub.logFatalCrash(
                                "Thread Interrupted Exception while waiting on cryptographic handshake.",
                                new IllegalStateException()
                        );
                    }
                }
                break;
            case OPTION_INDEX_PLAY_OFFLINE:
                playingOnline = false;
                EngineManager.startLocalEngine();
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
            case OPTION_INDEX_QUIT:
                System.exit(0);
        }
    }
    public void loginResponseAccountAlreadyConnected() {
        if (playingOnline) {
            statusColor = Color.ORANGE;
            statusMessage = "Account still online. Wait or try another login.";
            refreshScreen();
        } else {
            LogHub.logFatalCrash("Fatal Error - Account Already Connected to local engine.",
                    new IllegalStateException());
        }
    }

    public void loginResponseAccountDoesNotExist() {
        creatingNewAccount = true;
        if (playingOnline) {
            statusColor = Color.YELLOW;
            statusMessage = "Account does not exist. Confirm password to create.";
            savedPassword = enteredPassword;
            enteredPassword = "";
            refreshScreen();
        }
        else go(); //local account not yet created - simply try again using create rather than login
    }

    public void loginResponseDuplicateAccountCreation() {
        LogHub.logFatalCrash("Fatal Error - Duplicate Account Creation detected.",
                new IllegalStateException());
    }

    public void loginResponseIncorrectPassword() {
        if (playingOnline) {
            statusColor = Color.ORANGE;
            statusMessage = "Incorrect password. Re-enter or try another login.";
            enteredPassword = "";
            selectedIndex = 2;
            refreshScreen();
        } else {
            LogHub.logFatalCrash("Fatal Error - Incorrect Password Login Response from local engine.",
                    new IllegalStateException());
        }
    }

    public void loginResponseSuccess() {
        //todo - set PlayerSession account metadata
        //todo - go to an avatar selection screen here
        //for now, send a select avatar instruction that indicates we want to create a new avatar
        EngineManager.frontEndDataLink.transmit(new SelectAvatarInstructionData(-1));
        IOManager.setInputContext(new AvatarControlInputContext());
        IOManager.setOutputChannel(GUIConstants.CHANNEL_MAIN_GAME);
        IOManager.getGui().update(GUIConstants.CHANNEL_MAIN_GAME);
    }

    /**
     * Update the information used to draw this screen.
     * This must be called after any non-boolean state change in this class.
     */
    private void refreshScreen() {
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
            case VK_UP:
                if (--selectedIndex < 0) selectedIndex = OPTION_COUNT - 1;
                refreshScreen();
                break;
            case VK_DOWN:
                if (++selectedIndex >= OPTION_COUNT) selectedIndex = 0;
                refreshScreen();
                break;
            case VK_BACK_SPACE: case VK_DELETE:
                if (selectedIndex == OPTION_INDEX_ENTER_PASSWORD && enteredPassword.length() > 0) {
                    enteredPassword = enteredPassword.substring(0, enteredPassword.length() - 1);
                    refreshScreen();
                }
                else if (selectedIndex == OPTION_INDEX_ENTER_USERNAME && enteredUsername.length() > 0) {
                    enteredUsername = enteredUsername.substring(0, enteredUsername.length() - 1);
                    refreshScreen();
                }
                break;
                default:
                    char c = toChar(keyCode, keyMod);
                    if (c != ERROR) {
                        if (
                                selectedIndex == OPTION_INDEX_ENTER_PASSWORD &&
                                        enteredPassword.length() < Password.MAXIMUM_LENGTH
                        ) {
                            enteredPassword += c;
                            refreshScreen();
                        }
                        else if (
                                selectedIndex == OPTION_INDEX_ENTER_USERNAME &&
                                        enteredUsername.length() < USERNAME_LENGTH_MAX
                        ) {
                            enteredUsername += c;
                            refreshScreen();
                        }
                    }
        }
    }

    @Override
    public void handleKeyReleased(KeyEvent e) {/*nothing to do here */}

    public static LoginScreenInputContext get() {
        return (LoginScreenInputContext) DefinitionsManager.getLoginResponseHandler();
    }
}
