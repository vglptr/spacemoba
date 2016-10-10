package server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import shared.Point;
import shared.commands.Rpc;
import shared.gameobjects.GameObject;
import shared.gameobjects.Ship;

public class SocketThread extends Thread {
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    public boolean clientConnected = true;
    private Receiver receiver;
    private Sender sender;
    private Map<String, GameObject> gameObjects; // global objects
    private Map<String, GameObject> clientObjects; // per client objects
    private static int nextClientId = 0;
    private int currentClientId;

    public SocketThread(Socket socket, Map<String, GameObject> gameObjects) {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gameObjects = gameObjects;
        receiver = new Receiver(in, gameObjects, this);
        sender = new Sender(out, this);
    }

    public void run() {
        sendClientId();
        initGameObjects();
        while (clientConnected) {
            clientObjects = filterClientObjects(gameObjects);
            sender.send(clientObjects);
            receiver.receive();
            // execute game logic here
        }
    }

    private void sendClientId() {
        currentClientId = nextClientId;
        nextClientId++;
        Rpc idRequest = new Rpc();
        idRequest.command = "idRequest";
        idRequest.response = currentClientId;
        sender.send(idRequest);
    }

    private void initGameObjects() {
        Ship ship = new Ship(new Point(5, 5));
        gameObjects.put("ship" + currentClientId, ship);
    }

    private Map<String, GameObject> filterClientObjects(Map<String, GameObject> gameObjects) {
        // TODO:except gameobjects here, make all client specific modification
        // to clientobjects here, like fog of war
        //Map<String, GameObject> clientObjects = GameObjectUtils.select("ship", currentClientId, gameObjects);
        Map<String, GameObject> clientObjects = new HashMap<String, GameObject>(gameObjects);
        return clientObjects;
    }
}
