package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import shared.Point;

public class SocketThread extends Thread {
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;

	public SocketThread(Socket socket) {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Random r = new Random();
		int sendCounter = 0;
		while(true) {
			try {
				out.writeUnshared(new Point(r.nextFloat(), r.nextFloat()));
				// memory leak prevention of ObjectOutputStream,
				// it also resets ObjectInputStream on the other side
				if(sendCounter % 100000 == 0) {
					out.reset();
					sendCounter = 0;
				}
				sendCounter++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
