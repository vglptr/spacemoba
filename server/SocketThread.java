package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

import shared.GameObject;
import shared.Point;
import shared.commands.*;

import static shared.GameObjectConstants.*;

public class SocketThread extends Thread {
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Random r = new Random();
	private int sendCounter = 0;
	private HashMap<String, GameObject> gameObjects;

	public SocketThread(Socket socket, HashMap<String, GameObject> gameObjects) {
		this.gameObjects = gameObjects;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			send();
			receive();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void receive() {
		try {
			Object o = in.readUnshared();
			if (o instanceof Command) {
				System.out.println("received command: " + o.getClass());
				Command c = (Command) o;
				System.out.println("before command: " + gameObjects.get(SHIP01).getPosition());
				c.execute(gameObjects);
				System.out.println("after command: " + gameObjects.get(SHIP01).getPosition());
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	private void send() {
		try {
			out.writeUnshared(gameObjects);
			System.out.println("sending gameobject with: " + gameObjects.get(SHIP01).getPosition());
			out.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
