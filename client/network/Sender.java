package client.network;

import java.io.IOException;
import java.io.ObjectOutputStream;

import shared.commands.Command;

public class Sender {
	private ObjectOutputStream out;

	public Sender(ObjectOutputStream out) {
		this.out = out;
	}
	
	public void send(Command command) {
		System.out.println("sending command");
		try {
			out.writeUnshared(command);
			out.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
