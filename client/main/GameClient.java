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
	private static final int NETWORK_FREQUENCY = 50;
	private HashMap<String, GameObject> gameObjects = new HashMap<>();
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Socket socket;
	private Sender sender;
	private Receiver receiver;

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
		MainWindow mainWindow = new MainWindow();
		mainWindow.start();
	}

	private void mainLoop() {
		System.out.println("GAMECLIENT:" + Thread.currentThread());
		while (true) {
			gameObjects = receiver.receive();
			Move move = new Move(SHIP01, new Point(10, 10));
			sender.send(move);
			try {
				Thread.sleep(1 / NETWORK_FREQUENCY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
