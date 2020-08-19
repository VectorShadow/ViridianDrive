package frontend.io.inputcontext;

import backend.EngineManager;
import crypto.Password;
import definitions.DefinitionsManager;
import definitions.LoginResponseHandler;
import frontend.io.GUIConstants;
import frontend.io.IOManager;
import link.instructions.AccountCreationRequestInstructionDatum;
import link.instructions.LogInRequestInstructionDatum;
import main.LogHub;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static frontend.io.IOManager.setInputContext;
import static frontend.io.IOManager.setOutputChannel;
import static java.awt.event.KeyEvent.*;

import static definitions.ViridianDriveColors.*;

public class LoginScreenInputContext extends InputContext implements LoginResponseHandler {

    private static final int USERNAME_LENGTH_MAX = 16;
    private static final int USERNAME_LENGTH_MIN = 4;

    private Color statusColor = STATUS_INFO;
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
                    statusColor = STATUS_ALERT;
                    statusMessage = "Username must be " + USERNAME_LENGTH_MIN +
                            " - " + USERNAME_LENGTH_MAX + "characters.";
                    refreshScreen();
                } else if (
                        enteredPassword.length() < Password.MINIMUM_LENGTH ||
                                enteredPassword.length() > Password.MAXIMUM_LENGTH
                ) {
                    statusColor = STATUS_ALERT;
                    statusMessage = "Password must be "+ Password.MINIMUM_LENGTH +
                            " - " + Password.MAXIMUM_LENGTH + "characters.";
                    refreshScreen();
                } else if (creatingNewAccount && !enteredPassword.equals(savedPassword)) {
                    statusColor = STATUS_ALERT;
                    statusMessage = "Password confirmation failed. Try again.";
                    creatingNewAccount = false;
                    savedPassword = "";
                    enteredPassword = "";
                    refreshScreen();
                } else {
                    try {
                        EngineManager.connectToRemoteEngine();
                        statusColor = STATUS_PENDING;
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
                        statusColor = STATUS_ERROR;
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
            statusColor = STATUS_WARNING;
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
            statusColor = STATUS_ALERT;
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
            statusColor = STATUS_WARNING;
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
        setInputContext(new AvatarSelectionScreenInputContext());
        setOutputChannel(GUIConstants.CHANNEL_AVATAR_SELECTION);
        IOManager.getGui().update(GUIConstants.CHANNEL_AVATAR_SELECTION);
    }

    /**
     * Update the information used to draw this screen.
     * This must be called after any non-boolean state change in this class.
     */
    @Override
    protected void refreshScreen() {
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
