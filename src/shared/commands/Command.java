package shared.commands;

import java.util.Map;

import shared.gameobjects.GameObject;

public interface Command {
    void execute(Map<String, GameObject> gameObjects);
}
