package server.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.logging.Logger;

import shared.gameobjects.GameObject;

public class Sender {
    private final Logger LOGGER = Logger.getLogger(Sender.class.getName());
    private ObjectOutputStream out;
    private Map<String, GameObject> gameObjects;

    public Sender(ObjectOutputStream out, Map<String, GameObject> gameObjects) {
        this.out = out;
        this.gameObjects = gameObjects;
    }

    public void send() throws IOException {
        out.writeUnshared(gameObjects);
        LOGGER.info("gameobjects sent");
        out.reset();
    }
}
