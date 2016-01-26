package test.shared;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import shared.Point;
import shared.gameobjects.Ship;

public class ShipTest {

	@Test
	public void defaultPositionIsOrigin() {
		Ship ship = new Ship();
		Point origin = new Point(0, 0);
		assertTrue("Ship's initial coordinate is not in the origin", ship.getPosition().equals(origin));
	}

	@Test
	public void initialPositionCanBeSetViaConstructor() {
		Point p4_3 = new Point(4, 3);
		Ship ship = new Ship(p4_3);
		assertTrue("Could not set ship's position to (4;3)", ship.getPosition().equals(p4_3));
	}

	@Test
	public void shipCanMove() {
		Ship ship = new Ship();
		Point point = new Point(4, 5);
		ship.move(point);
		assertTrue("Ship did not move by the correct amount", ship.getPosition().equals(point));

		Point point2 = new Point(3.3f, 3.3f);
		ship.move(point2);
		Point point3 = new Point();
		point3.add(point);
		point3.add(point2);
		assertTrue("Ship did not move by the correct amount", ship.getPosition().equals(point3));
	}
}
