package server;

import static shared.gameobjects.GameObjectConstants.SHIP01;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import shared.gameobjects.GameObject;

public class Sender {
	private ObjectOutputStream out;
	private Map<String, GameObject> gameObjects;

	public Sender(ObjectOutputStream out,
			Map<String, GameObject> gameObjects) {
		this.out = out;
		this.gameObjects = gameObjects;
	}

	public void send() throws IOException {
			out.writeUnshared(gameObjects);
			System.out.println("sending gameobject with: "
					+ gameObjects.get(SHIP01).getPosition());
			out.reset();
	}
}
