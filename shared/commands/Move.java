package shared.commands;

import java.io.Serializable;
import java.util.Map;

import shared.Point;
import shared.gameobjects.GameObject;

@SuppressWarnings("serial")
public class Move implements Command, Serializable {
	private String target;
	private Point delta;

	public Move(String target, Point delta) {
		this.target = target;
		this.delta = delta;
	}
	
	@Override
	public void execute(Map<String, GameObject> gameObjects) {
		gameObjects.get(target).move(delta);
	}
}
