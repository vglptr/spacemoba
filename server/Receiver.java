package server;

import static shared.gameobjects.GameObjectConstants.SHIP01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import shared.commands.Command;
import shared.gameobjects.GameObject;

public class Receiver {
	private ObjectInputStream in;
	private Map<String, GameObject> gameObjects;

	public Receiver(ObjectInputStream in,
			Map<String, GameObject> gameObjects) {
		this.in = in;
		this.gameObjects = gameObjects;
	}

	public void receive() throws IOException {
		try {
			Object o = in.readUnshared();
			if (o instanceof Command) {
				System.out.println("received command: " + o.getClass());
				Command c = (Command) o;
				System.out.println("before command: "
						+ gameObjects.get(SHIP01).getPosition());
				c.execute(gameObjects);
				System.out.println("after command: "
						+ gameObjects.get(SHIP01).getPosition());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
