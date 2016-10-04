package client.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Logger;

import shared.commands.Command;

public class Sender {
    private final Logger LOGGER = Logger.getLogger(Sender.class.getName());
    private ObjectOutputStream out;

    public Sender(ObjectOutputStream out) {
        this.out = out;
    }

    public void send(List<Command> commands) {
        LOGGER.info("sending command: " + commands);
        try {
            out.writeUnshared(commands);
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
