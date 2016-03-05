package server;

import static shared.gameobjects.GameObjectConstants.SHIP01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import shared.commands.Command;
import shared.gameobjects.GameObject;

public class SocketThread extends Thread {
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	boolean clientConnected = true;
	private Receiver receiver;
	private Sender sender;

	public SocketThread(Socket socket, HashMap<String, GameObject> gameObjects) {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		receiver = new Receiver(in, gameObjects);
		sender = new Sender(out, gameObjects);
	}

	public void run() {
		while (clientConnected) {
			try {
				sender.send();
				receiver.receive();
				//execute game logic on gameObjects here
			} catch (IOException e1) {
				System.out.println("client disconnected");
				clientConnected = false;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
