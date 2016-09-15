package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.jme3.system.AppSettings;

import client.gui.MainWindow;
import client.network.Receiver;
import client.network.Sender;
import shared.commands.Command;
import shared.commands.Rpc;

public class GameClient {
    private final Logger LOGGER = Logger.getLogger(GameClient.class.getPackage().getName());
    private FileHandler fileHandler;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    public GameClient(String serverIp) {
        LOGGER.setLevel(Level.OFF);
        try {
            fileHandler = new FileHandler("client.log");
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
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
    }

    private void startNetwork() {
        sender = new Sender(out);
        receiver = new Receiver(in);
        LOGGER.info("networking started");
    }

    private void startGui() {
        MainWindow mainWindow = new MainWindow();
        AppSettings settings = new AppSettings(true);
        settings.setVSync(true);
        // settings.setFrameRate(120); //game runs at 8000 fps currently
        mainWindow.setClient(this);
        mainWindow.setSettings(settings);
        mainWindow.start();
        LOGGER.info("gui started");
    }

    public void send(Command command) {
        sender.send(command);
    }

    public void receive() {
        S.gameObjects = receiver.receive();
    }

    public void close() {
        System.exit(0);
    }
}
