package backend;

import definitions.*;
import frontend.io.inputcontext.LoginScreenInputContext;
import main.LogHub;
import util.ArgumentHandler;

import java.io.IOException;

public class RemoteDriver {
    public static void main(String[] args) {
        ArgumentHandler.handle(args);
        DefinitionsManager.loadDefinitions(
                new ViridianDriveAvatarManager(),
                new ViridianDriveFeatureHandler(),
                new ViridianDriveGameZoneGenerator(),
                new ViridianDriveGameZoneUpdateListener(),
                new LoginScreenInputContext(),
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
