package frontend;

import frontend.io.IOManager;
import main.LogHub;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UserPreferences {
    private static final String PREFERENCES_DIRECTORY = "./pref";
    private static final Path PREFERENCES_FILE_PATH = Paths.get(PREFERENCES_DIRECTORY + "/local_preferences.txt");

    private static final char LINE_BREAK = '\n';
    private static final char SEPARATOR = '=';

    private static final String FIELD_FS = "fullScreen";
    private static final String FIELD_GFX = "graphics";

    private static final String VAL_F = "false";
    private static final String VAL_T = "true";

    public static UserPreferences instance = null;

    public boolean fullScreen = false;
    public boolean graphics = false;

    public static UserPreferences getInstance() {
        if (instance == null) {
            instance = new UserPreferences();
            BufferedReader preferencesReader = readPreferences();
            String preferenceLine;
            String[] parsedPreferenceLine;
            String field;
            String value;
            try {
                while ((preferenceLine = preferencesReader.readLine()) != null) {
                    parsedPreferenceLine = parsePreferenceLine(preferenceLine);
                    field = parsedPreferenceLine[0];
                    value = parsedPreferenceLine[1];
                    switch (field) {
                        case FIELD_FS:
                            switch (value) {
                                case VAL_F:
                                    instance.fullScreen = false;
                                    break;
                                case VAL_T:
                                    instance.fullScreen = true;
                                    break;
                            }
                            break;
                        case FIELD_GFX:
                            switch (value) {
                                case VAL_F:
                                    instance.graphics = false;
                                    break;
                                case VAL_T:
                                    instance.graphics = true;
                                    break;
                            }
                            break;
                    }
                }
                instance.update();
            } catch (IOException e) {
                LogHub.logFatalCrash("Error reading user preferences", e);
            }

        }
        return instance;
    }

    public static Path getDirectoryPath() {
        return Paths.get(PREFERENCES_DIRECTORY);
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public boolean isGraphics() {
        return graphics;
    }

    private static String[] parsePreferenceLine(String preferenceLine) {
        int nextSeparator = preferenceLine.indexOf(SEPARATOR);
        if (nextSeparator < 0)
            throw new IllegalStateException("Improper preference line - separator not found.");
        String[] lineData = new String[2];
        lineData[0] = preferenceLine.substring(0, nextSeparator);
        lineData[1] = preferenceLine.substring(nextSeparator + 1);
        return lineData;
    }

    /**
     * Open the user catalog for reading.
     * @return a BufferedReader for the user catalog.
     */
    private static BufferedReader readPreferences() {
        BufferedReader br = null;
        try {
            if (!Files.exists(Paths.get(PREFERENCES_DIRECTORY)))
                Files.createDirectory(Paths.get(PREFERENCES_DIRECTORY));
            if (!Files.exists(PREFERENCES_FILE_PATH))
                Files.createFile(PREFERENCES_FILE_PATH);
            br = Files.newBufferedReader(PREFERENCES_FILE_PATH);
        } catch (IOException e) {
            LogHub.logFatalCrash("Failed to validate user preferences.", e);
        }
        return br;
    }

    public void toggleFullScreen() {
        fullScreen = !fullScreen;
        update();
        IOManager.getGui().toggleFullScreenMode();
    }

    public void toggleGraphics() {
        graphics = !graphics;
        update();
    }

    private void update() {
        StringBuilder contents = new StringBuilder();
        contents.append(FIELD_FS).append(SEPARATOR).append(fullScreen ? VAL_T : VAL_F).append(LINE_BREAK);
        contents.append(FIELD_GFX).append(SEPARATOR).append(graphics ? VAL_T : VAL_F).append(LINE_BREAK);
        try {
            Files.write(PREFERENCES_FILE_PATH, contents.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            LogHub.logFatalCrash("IOException during preference update.", e);
        }
    }
}
