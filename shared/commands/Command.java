package shared.commands;

import java.util.HashMap;

import shared.GameObject;

public interface Command {
	public void execute(HashMap<String, GameObject> gameObjects);
}
