package shared;

import java.util.logging.LogManager;

import client.GameClient;
import server.GameServer;

public class Start {

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        if (args[0].equals("server")) {
            new GameServer();
        } else if (args[0].equals("client")) {
            new GameClient(args[1]);
        }
    }
}
