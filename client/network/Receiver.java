package client.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.logging.Logger;

import shared.gameobjects.GameObject;

public class Receiver {
    private final Logger LOGGER = Logger.getLogger(Receiver.class.getName());
    private ObjectInputStream in;

    public Receiver(ObjectInputStream in) {
        this.in = in;
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, GameObject> receive() {
        try {
            HashMap<String, GameObject> gameObjects = (HashMap<String, GameObject>) in.readUnshared();
            LOGGER.info("gameobjects received");
            return gameObjects;
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
