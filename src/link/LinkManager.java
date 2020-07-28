package link;

import main.Client;
import main.Engine;
import main.Server;

import java.io.IOException;
import java.util.ArrayList;

public class LinkManager {

    private static final String HOST_NAME = "vps244728.vps.ovh.ca";

    private static final int PORT_NUMBER = 29387;

    public static DataLink connectToRemoteEngine() throws IOException {
        return Client.connect(new FrontendDataHandler(), HOST_NAME, PORT_NUMBER);
    }

    public static DataLink startLocalEngine() {
        LocalDataLink front = new LocalDataLink(new FrontendDataHandler());
        LocalDataLink back = new LocalDataLink(new BackendDataHandler());
        LocalDataLink.pair(front, back);
        ArrayList<DataLink> engineLinks = new ArrayList<>();
        engineLinks.add(back);
        new Engine(engineLinks).start();
        return front;
    }

    public static void startRemoteEngine() throws IOException {
        Server server = new Server(new BackendDataHandler(), PORT_NUMBER);
        new Engine(server.getOpenDataLinks()).start();
        server.start();
    }


}
