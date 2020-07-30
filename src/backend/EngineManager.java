package backend;

import link.BackendDataHandler;
import link.DataLink;
import link.FrontendDataHandler;
import link.LocalDataLink;
import main.Client;
import main.Engine;
import main.Server;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Provides various engine related services.
 */
public class EngineManager {

    private static final String HOST_NAME = "vps244728.vps.ovh.ca";

    private static final int PORT_NUMBER = 29387;

    private static Engine engine = null;

    /**
     * Initiate a connection to a remote engine.
     * @return the front end facing remote data link associated with the socket on which the connection was made
     * @throws IOException passed from Client.connect().
     */
    public static DataLink connectToRemoteEngine() throws IOException {
        return Client.connect(new FrontendDataHandler(), HOST_NAME, PORT_NUMBER);
    }

    /**
     * Access the engine.
     * Backend applications must first call startRemoteEngine() or this call will fail.
     * Frontend applications running locally must first call startLocalEngine() or this call will fail.
     * Frontend applications running remotely may not use this call, and should have no need to do so.
     * @return the engine for this application.
     */
    public static Engine getEngine() {
        if (engine == null)
            throw new IllegalStateException("Engine must be started before it can be accessed.");
        return engine;
    }

    /**
     * Start a locally running engine.
     * @return the frontend data link connected to the engine.
     */
    public static DataLink startLocalEngine() {
        LocalDataLink front = new LocalDataLink(new FrontendDataHandler());
        LocalDataLink back = new LocalDataLink(new BackendDataHandler());
        LocalDataLink.pair(front, back);
        ArrayList<DataLink> engineLinks = new ArrayList<>();
        engineLinks.add(back);
        engine = new Engine(engineLinks);
        engine.start();
        return front;
    }

    /**
     * Start a remotely running server and engine.
     * We first construct the server, then the engine using the server's datalinks.
     * First we start the engine to ensure that the datalinks are empty, then we start the server to wait for client
     * connections.
     * @throws IOException passed from Server constructor.
     */
    static void startRemoteEngine() throws IOException {
        Server server = new Server(new BackendDataHandler(), PORT_NUMBER);
        engine = new Engine(server.getOpenDataLinks());
        engine.start();
        server.start();
    }


}