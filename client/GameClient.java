package client;

import static shared.gameobjects.GameObjectConstants.SHIP01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import client.gui.MainWindow;
import client.network.Receiver;
import client.network.Sender;
import shared.Point;
import shared.commands.Move;

public class GameClient {
    private final Logger LOGGER = Logger.getLogger(GameClient.class.getPackage().getName());
    private FileHandler fileHandler;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    public GameClient(String serverIp) {
        try {
            fileHandler = new FileHandler("client.log");
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
        LOGGER.setLevel(Level.INFO);
        LOGGER.info(String.format("connecting to %s", serverIp));
        try {
            socket = new Socket(serverIp, 55555);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            LOGGER.info("Connection failed");
            LOGGER.info(e.getMessage());
            LOGGER.info("Exiting");
            System.exit(1);
        }
        startNetwork();
        startGui();
        mainLoop();
    }

    private void startNetwork() {
        sender = new Sender(out);
        receiver = new Receiver(in);
        LOGGER.info("networking started");
    }

    private void startGui() {
        MainWindow mainWindow = new MainWindow();
        mainWindow.start();
        LOGGER.info("gui started");
    }

    private void mainLoop() {
        while (true) {
            S.setGameObjects(receiver.receive());
            // execute all logic here
            Move move = new Move(SHIP01, new Point(1.0f, 1.0f));
            sender.send(move);
        }
    }
}
