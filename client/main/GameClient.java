package client.main;

import static shared.gameobjects.GameObjectConstants.SHIP01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import shared.Point;
import shared.commands.Move;
import shared.gameobjects.GameObject;
import shared.gameobjects.Ship;
import client.gui.MainWindow;
import client.network.Receiver;
import client.network.Sender;

public class GameClient {
	private HashMap<String, GameObject> gameObjects;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Socket socket;
	private Sender sender;
	private Receiver receiver;
	private MainWindow mainWindow;

	public GameClient(String serverIp) {
		try {
			socket = new Socket(serverIp, 55555);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Connection failed");
			System.out.println(e.getMessage());
			System.out.println("Exiting");
			System.exit(1);
		}
		startNetwork();
		startGui();
		mainLoop();
	}

	private void startNetwork() {
		sender = new Sender(out);
		receiver = new Receiver(in);
	}

	private void startGui() {
		mainWindow = new MainWindow(gameObjects);
		mainWindow.start();
	}

	private void mainLoop() {
		while (true) {
			gameObjects = receiver.receive();
			System.out.println("size in client mainloop: " + gameObjects.size());
			
			mainWindow.setGameObjects(gameObjects);
			
			//execute all logic here
			Move move = new Move(SHIP01, new Point(0.1f, 0.1f));
			
			sender.send(move);
		}
	}
	
	public HashMap<String, GameObject> getGameObjects() {
		return gameObjects;
	}
}
