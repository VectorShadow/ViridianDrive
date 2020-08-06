package backend;

import link.BackendDataHandler;
import link.DataLink;
import link.FrontendDataHandler;
import link.LocalDataLink;
import main.Client;
import main.Engine;
import main.Server;
import main.DataLinkToZoneAggregator;

import java.io.IOException;

/**
 * Provides various engine related services.
 */
public class EngineManager {

    public static DataLink frontEndDataLink = null;

    private static final String HOST_NAME = "vps244728.vps.ovh.ca";

    private static final int PORT_NUMBER = 29387;

    /**
     * Initiate a connection to a remote engine.
     * @throws IOException passed from Client.connect().
     */
    public static void connectToRemoteEngine() throws IOException {
        frontEndDataLink = Client.connect(new FrontendDataHandler(), HOST_NAME, PORT_NUMBER);
    }

    /**
     * Start a locally running engine.
     */
    public static void startLocalEngine() {
        LocalDataLink front = new LocalDataLink(new FrontendDataHandler());
        LocalDataLink back = new LocalDataLink(new BackendDataHandler());
        LocalDataLink.pair(front, back);
        DataLinkToZoneAggregator aggregator = new DataLinkToZoneAggregator();
        aggregator.addDataLink(back);
        Engine.startEngine(aggregator);
        frontEndDataLink = front;
    }

    /**
     * Start a remotely running server and engine.
     * We first construct the server, then the engine using the server's datalinks.
     * First we start the engine to ensure that the datalinks are empty, then we start the server to wait for client
     * connections.
     * @throws IOException passed from Server constructor.
     */
    static void startRemoteEngine() throws IOException {
        DataLinkToZoneAggregator aggregator = new DataLinkToZoneAggregator();
        Server server = new Server(new BackendDataHandler(), aggregator, PORT_NUMBER);
        Engine.startEngine(aggregator);
        server.start();
    }


}
