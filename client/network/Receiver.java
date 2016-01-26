package client.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import shared.gameobjects.GameObject;

public class Receiver {
	private ObjectInputStream in;

	public Receiver(ObjectInputStream in) {
		this.in = in;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, GameObject> receive() {
		Object o = null;
		try {
			o = in.readUnshared();
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		if (o instanceof HashMap<?, ?>) {
			return (HashMap<String, GameObject>) o;
		} else {
			return null;
		}
	}
}