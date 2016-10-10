package server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

import shared.commands.Command;
import shared.commands.SetPosition;
import shared.commands.SetRotation;
import shared.gameobjects.GameObject;

public class Receiver {
    private ObjectInputStream in;
    private Map<String, GameObject> gameObjects;
    private SocketThread socketThread;

    public Receiver(ObjectInputStream in, Map<String, GameObject> gameObjects, SocketThread socketThread) {
        this.in = in;
        this.gameObjects = gameObjects;
        this.socketThread = socketThread;
    }

    @SuppressWarnings("unchecked")
    public void receive() {
        Object object = null;
        try {
            object = in.readUnshared();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            socketThread.clientConnected = false;
        }
        
        if (object instanceof List<?>) {
            for (Command command : (List<Command>) object) {
                if (command instanceof SetPosition) {
                    SetPosition setPosition = (SetPosition) command;
                    gameObjects.get(setPosition.getTarget()).setPosition(setPosition.getPosition());
                }
                if (command instanceof SetRotation) {
                    SetRotation setRotation = (SetRotation) command;
                    gameObjects.get(setRotation.getTarget()).setRotation(setRotation.getRotation());
                    //System.out.println("ROTATION " + setRotation.getRotation());
                }
            }
        }

    }
}
