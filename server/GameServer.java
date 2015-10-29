package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
	ServerSocket serverSocket = null;

	public GameServer() {
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
				System.out.println("[SERVER] server started");
				Socket socket = serverSocket.accept();
				SocketThread socketThread = new SocketThread(socket);
				socketThread.start();
				System.out.println("[SERVER] client connected");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
