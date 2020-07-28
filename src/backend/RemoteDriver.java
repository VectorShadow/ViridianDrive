package backend;

import main.LogHub;

import java.io.IOException;

public class RemoteDriver {
    public static void main(String[] args) {
        try {
            EngineManager.startRemoteEngine();
        } catch (IOException e) {
            LogHub.logFatalCrash("Error starting server.", e);
        }
    }
}
