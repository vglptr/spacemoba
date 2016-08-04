package server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.logging.Logger;

import shared.commands.Command;
import shared.gameobjects.GameObject;

public class Receiver {
    private final Logger LOGGER = Logger.getLogger(Receiver.class.getName());
    private ObjectInputStream in;
    private Map<String, GameObject> gameObjects;

    public Receiver(ObjectInputStream in, Map<String, GameObject> gameObjects) {
        this.in = in;
        this.gameObjects = gameObjects;
    }

    public void receive() throws IOException {
        try {
            Command c = (Command) in.readUnshared();
            LOGGER.info("received command: " + c.getClass());
            c.execute(gameObjects);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
