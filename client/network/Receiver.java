package client.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import client.S;
import shared.commands.Rpc;
import shared.gameobjects.GameObject;

public class Receiver {
    private final Logger LOGGER = Logger.getLogger(Receiver.class.getName());
    private ObjectInputStream in;

    public Receiver(ObjectInputStream in) {
        this.in = in;
    }

    @SuppressWarnings("unchecked")
    public Map<String, GameObject> receive() {
        try {
            Object o = in.readUnshared();
            if(o instanceof Map<?, ?>) {
                Map<String, GameObject> gameObjects = (HashMap<String, GameObject>)o;
                LOGGER.info("gameobjects received");
                return gameObjects;
            }
            if(o instanceof Rpc) {
                Rpc rpc = (Rpc)o;
                if(rpc.command.equals("idRequest")) {
                    S.clientId = (int)rpc.response;
                }
            }
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
