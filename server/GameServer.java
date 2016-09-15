package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import server.network.SocketThread;
import shared.gameobjects.GameObject;

public class GameServer {
    private final Logger LOGGER = Logger.getLogger(GameServer.class.getPackage().getName());
    private FileHandler fileHandler;
    private Map<String, GameObject> gameObjects = new HashMap<>();
    private ServerSocket serverSocket = null;

    public GameServer() {
        LOGGER.setLevel(Level.FINE);
        try {
            fileHandler = new FileHandler("server.log");
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
        try {
            serverSocket = new ServerSocket(55555);
        } catch (IOException e) {
            e.printStackTrace();
        }
        acceptConnections();
    }

    private void acceptConnections() {
        try {
            while (true) {
                LOGGER.info("waiting for client connections");
                Socket socket = serverSocket.accept();
                SocketThread socketThread = new SocketThread(socket, gameObjects);
                socketThread.start();
                LOGGER.info(String.format("client connected: %s", socket.getRemoteSocketAddress()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
