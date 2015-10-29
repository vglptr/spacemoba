package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.Point;

public class GameClient {
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Socket socket;
	
	public GameClient(String serverIp) {
		try {
			socket = new Socket(serverIp, 55555);
			out =new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainLoop();
	}
	
	private void mainLoop() {
		int i = 0;
		Object o = null;
		while(true) {
			try {
				o = in.readUnshared();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			if(o instanceof Point) {
				Point p = (Point)o;
				if(i % 100 == 0) {
					System.out.println(i + ". " + p.x + " " + p.y);
				}
				i++;
			}
		}
	}

}
