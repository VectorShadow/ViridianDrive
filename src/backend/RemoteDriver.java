package backend;

import definitions.*;
import main.LogHub;
import util.ArgumentHandler;

import java.io.IOException;

public class RemoteDriver {
    public static void main(String[] args) {
        ArgumentHandler.handle(args);
        DefinitionsManager.loadDefinitions(
                new ViridianDriveGameZoneGenerator(),
                new ViridianDriveGameZoneUpdateListener(),
                new ViridianDriveOrderExecutor(),
                new ViridianDriveTerrainLookup()
        );
        try {
            EngineManager.startRemoteEngine();
        } catch (IOException e) {
            LogHub.logFatalCrash("Error starting server.", e);
        }
    }
}
