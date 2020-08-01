package backend;

import definitions.DefinitionsManager;
import definitions.ViridianDriveGameZoneGenerator;
import definitions.ViridianDriveTerrainLookup;
import main.LogHub;
import util.ArgumentHandler;

import java.io.IOException;

public class RemoteDriver {
    public static void main(String[] args) {
        ArgumentHandler.handle(args);
        DefinitionsManager.loadDefinitions(
                new ViridianDriveGameZoneGenerator(),
                new ViridianDriveTerrainLookup()
        );
        try {
            EngineManager.startRemoteEngine();
        } catch (IOException e) {
            LogHub.logFatalCrash("Error starting server.", e);
        }
    }
}
