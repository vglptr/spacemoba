package shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ship extends GameObject implements Serializable {
	private Point destination;

	public Ship() {
		position = new Point(0, 0);
	}

	public Ship(Point position) {
		this.position = position;
	}

	public Point getDestination() {
		return destination;
	}

	public void setDestination(Point destination) {
		this.destination = destination;
	}
}
