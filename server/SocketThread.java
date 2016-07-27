package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import shared.gameobjects.GameObject;

public class SocketThread extends Thread {
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private boolean clientConnected = true;
	private Receiver receiver;
	private Sender sender;

	public SocketThread(Socket socket, Map<String, GameObject> gameObjects) {
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
				//execute game logic on gameObjects in receiver.receive()
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
