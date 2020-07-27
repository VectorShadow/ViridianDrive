package backend;

import link.BackendDataHandler;
import main.Engine;
import main.LogHub;
import main.Server;

import java.io.IOException;

public class RemoteDriver {
    public static void main(String[] args) {
        try {
            Server server = new Server(new BackendDataHandler(), 29387);
            server.start();
            Engine engine; //todo - more here, probably
        } catch (IOException e) {
            LogHub.logFatalCrash("IOException during server operation.", e);
        }
    }
}
