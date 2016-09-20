package server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.logging.Logger;

import shared.commands.Command;
import shared.commands.Move;
import shared.commands.SetPosition;
import shared.gameobjects.GameObject;

public class Receiver {
    private final Logger LOGGER = Logger.getLogger(Receiver.class.getName());
    private ObjectInputStream in;
    private Map<String, GameObject> gameObjects;
    private SocketThread socketThread;

    public Receiver(ObjectInputStream in, Map<String, GameObject> gameObjects, SocketThread socketThread) {
        this.in = in;
        this.gameObjects = gameObjects;
        this.socketThread = socketThread;
    }

    public void receive() {
        Object o = null;
            try {
                o = in.readUnshared();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                socketThread.clientConnected = false;
                LOGGER.info("client disconnected");
            }
        if (o instanceof Command) {
            if (o instanceof SetPosition) {
                LOGGER.info("SetPosition command");
                SetPosition setPosition = (SetPosition) o;
                gameObjects.get(setPosition.getTarget()).setPosition(setPosition.getPosition());
            }
        }
    }
}
