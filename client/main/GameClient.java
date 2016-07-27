package client.main;

import static shared.gameobjects.GameObjectConstants.SHIP01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import client.gui.MainWindow;
import client.network.Receiver;
import client.network.Sender;
import shared.Point;
import shared.commands.Move;
import shared.gameobjects.GameObject;

public class GameClient {
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
		while (true) {
			S.setGameObjects(receiver.receive());
			if (S.getGameObjects() != null) {
				System.out.println("size in client mainloop: " + S.getGameObjects().size());
			}

			// execute all logic here
			Move move = new Move(SHIP01, new Point(1.0f, 1.0f));

			sender.send(move);
		}
	}
}
