package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import shared.GameObject;
import shared.Point;
import shared.Ship;
import static shared.GameObjectConstants.*;

public class GameServer {
	public HashMap<String, GameObject> gameObjects = new HashMap<>();
	private int nextId;
	private ServerSocket serverSocket = null;

	public GameServer() {
		initGameObjects();
		try {
			serverSocket = new ServerSocket(55555);
		} catch (IOException e) {
			e.printStackTrace();
		}
		acceptConnections();
	}

	public int getNextId() {
		return nextId++;
	}

	private void initGameObjects() {
		Ship ship = new Ship(new Point(5, 5));
		ship.setDestination(new Point(9, 9));
		gameObjects.put(SHIP01, ship);
		System.out.println("initial ship: " + gameObjects.get(SHIP01).getPosition());
	}

	private void acceptConnections() {
		try {
			while (true) {
				System.out.println("[SERVER] waiting for client connections");
				Socket socket = serverSocket.accept();
				SocketThread socketThread = new SocketThread(socket, gameObjects);
				socketThread.start();
				System.out.println("[SERVER] client connected");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
