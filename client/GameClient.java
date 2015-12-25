package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import shared.GameObject;
import shared.Point;
import shared.Ship;
import shared.commands.Command;
import shared.commands.Move;

import static shared.GameObjectConstants.*;

public class GameClient {
	private HashMap<String, GameObject> gameObjects = new HashMap<>();
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Socket socket;

	public GameClient(String serverIp) {
		try {
			socket = new Socket(serverIp, 55555);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLoop();
	}

	private void mainLoop() {
		while (true) {
			receive();

			Move move = new Move(SHIP01, new Point(10, 10));

			send(move);
			// try {
			// Thread.sleep(5000);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
	}

	@SuppressWarnings("unchecked")
	private void receive() {
		Object o = null;
		try {
			o = in.readUnshared();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		if (o instanceof HashMap<?, ?>) {
			gameObjects = (HashMap<String, GameObject>) o;
			System.out.println("gameObjects received");
		}

		System.out.println("client gets: " + gameObjects.get(SHIP01).getPosition());
		System.out.println("client gets dest: " + ((Ship) (gameObjects.get(SHIP01))).getDestination());
	}

	private void send(Command command) {
		System.out.println("sending command");
		try {
			out.writeUnshared(command);
			out.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// }
	}
}
