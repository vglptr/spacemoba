package client.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

import shared.commands.Command;

public class Sender {
    private final Logger LOGGER = Logger.getLogger(Sender.class.getName());
    private ObjectOutputStream out;

    public Sender(ObjectOutputStream out) {
        this.out = out;
    }

    public void send(Command command) {
        LOGGER.info("sending command: " + command.getClass());
        try {
            out.writeUnshared(command);
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
