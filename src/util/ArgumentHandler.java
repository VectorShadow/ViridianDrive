package util;

import frontend.UserPreferences;
import main.LiveLog;
import main.LogHub;
import user.UserAccountManager;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ArgumentHandler {
    public static void main(String[] args) {
        handle(args);
    }
    public static void handle(String[] args) {
        if (args.length == 0) return;
        switch (args[0]) {
            case "clean":
                for (int i = 1; i < args.length; ++i) {
                    String arg = args[i];
                    switch (arg) {
                        case "log":
                            LiveLog.stop(); //terminate any active logging
                            removeRecursively(LogHub.getDirectoryPath());
                            break;
                        case "prf":
                            removeRecursively(UserPreferences.getDirectoryPath());
                            break;
                        case "usr":
                            removeRecursively(UserAccountManager.getDirectoryPath());
                            break;
                            //todo - others?
                    }
                }
                break;
                //todo - others?
        }
    }
    private static void removeRecursively(Path directoryPath) {
        if (Files.exists(directoryPath)) {
            try {
                Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                LogHub.logNonFatalError("Error while removing directory at path " + directoryPath, e);
            }
        }
    }
}
