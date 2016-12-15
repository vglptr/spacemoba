package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.jme3.system.AppSettings;

import client.gui.MainWindow;
import client.network.Receiver;
import client.network.Sender;
import shared.commands.Command;

public class GameClient {
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    public GameClient(String serverIp) {
        try {
            socket = new Socket(serverIp, 55555);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.exit(1);
        }
        startNetwork();
        startGui();
    }

    private void startNetwork() {
        sender = new Sender(out);
        receiver = new Receiver(in);
        receiver.receive(); // to get clientid before gui starts
    }

    private void startGui() {
        MainWindow mainWindow = new MainWindow();
        AppSettings settings = new AppSettings(true);
        settings.setVSync(true);
        //settings.setFrameRate(60); //game runs at 8000 fps currently
        mainWindow.setClient(this);
        mainWindow.setSettings(settings);
        mainWindow.start();
    }

    public void send(List<Command> commands) {
        sender.send(commands);
    }

    public void receive() {
        S.gameObjects = receiver.receive();
    }

    public void close() {
        System.exit(0);
    }
}
