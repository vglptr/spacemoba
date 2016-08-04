package test.client;

import org.junit.Test;

import client.GameClient;
import server.GameServer;

public class GameClientTest {

    @Test
    public void ClientMovementAppearsOnOtherClient() {
        GameServer server = new GameServer();
        GameClient gameClient1 = new GameClient("localhost");
        GameClient gameClient2 = new GameClient("localhost");

    }
}
