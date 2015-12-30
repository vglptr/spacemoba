package test.shared;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import shared.Level;
import shared.gameobjects.Ship;

public class LevelTest {
	@Test
	public void levelHasAFunctionalGameObjects() {
		Ship ship = new Ship();
		Level.getInstance().getGameObjects().add(ship);
		assertTrue("Gameobjects stored in level are not the same", Level.getInstance().getGameObjects().get(0).equals(ship));
	}
}
