package backend;

import link.LinkManager;
import main.LogHub;

import java.io.IOException;

public class RemoteDriver {
    public static void main(String[] args) {
        try {
            LinkManager.startRemoteEngine();
        } catch (IOException e) {
            LogHub.logFatalCrash("Error starting server.", e);
        }
    }
}
