package server.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import shared.commands.Command;
import shared.commands.Rpc;
import shared.gameobjects.GameObject;

public class Sender {
    private ObjectOutputStream out;
    private SocketThread socketThread;

    public Sender(ObjectOutputStream out, SocketThread socketThread) {
        this.out = out;
        this.socketThread = socketThread;
    }

    public void send(Map<String, GameObject> gameObjects) {
        try {
            out.writeUnshared(gameObjects);
            out.reset();
        } catch (IOException e) {
            socketThread.clientConnected = false;
            e.printStackTrace();
        }
    }
    
    public void send(Rpc rpc) {
        try {
            out.writeUnshared(rpc);
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
