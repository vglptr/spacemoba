package server;

import static shared.gameobjects.GameObjectConstants.SHIP01;

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
import shared.Point;
import shared.gameobjects.GameObject;
import shared.gameobjects.Ship;

public class GameServer {
    private final Logger LOGGER = Logger.getLogger(GameServer.class.getPackage().getName());
    private FileHandler fileHandler;
    public Map<String, GameObject> gameObjects = new HashMap<>();
    private ServerSocket serverSocket = null;

    public GameServer() {
        try {
            fileHandler = new FileHandler("server.log");
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
        initGameObjects();
        try {
            serverSocket = new ServerSocket(55555);
        } catch (IOException e) {
            e.printStackTrace();
        }
        acceptConnections();
    }

    private void initGameObjects() {
        Ship ship = new Ship(new Point(5, 5));
        ship.setDestination(new Point(9, 9));
        gameObjects.put(SHIP01, ship);
        LOGGER.info(String.format("initial ship: %s", gameObjects.get(SHIP01).getPosition()));
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
