package shared;

import client.GameClient;
import server.GameServer;

public class Start {

	public static void main(String[] args) {
		if(args[0].equals("server")) {
			new GameServer();
		}
		else if(args[0].equals("client")) {
			new GameClient(args[1]);
		}
	}

}
