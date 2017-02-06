package shared.commands;

import java.io.Serializable;
import java.util.Map;

import shared.Point;
import shared.gameobjects.GameObject;

@SuppressWarnings("serial")
public class SetPosition implements Command, Serializable {
    private String target;
    private Point position;

    public SetPosition(String target, Point position) {
        this.target = target;
        this.position = position;
    }

    @Override
    public void execute(Map<String, GameObject> gameObjects) {
        gameObjects.get(target).setPosition(position);
    }
}
