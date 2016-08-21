package server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

import shared.gameobjects.GameObject;

public class SocketThread extends Thread {
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private boolean clientConnected = true;
    private Receiver receiver;
    private Sender sender;
    private final Logger LOGGER = Logger.getLogger(SocketThread.class.getName());

    public SocketThread(Socket socket, Map<String, GameObject> gameObjects) {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        receiver = new Receiver(in, gameObjects);
        sender = new Sender(out, gameObjects);
    }

    public void run() {
        while (clientConnected) {
            try {
                sender.send();
                receiver.receive();
                // execute game logic on gameObjects in receiver.receive()
            } catch (IOException e1) {
                LOGGER.info("client disconnected");
                clientConnected = false;
            }
            
        }
    }
}
