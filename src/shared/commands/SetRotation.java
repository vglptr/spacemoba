package shared.commands;

import java.io.Serializable;
import java.util.Map;

import com.jme3.math.Quaternion;

import shared.gameobjects.GameObject;

@SuppressWarnings("serial")
public class SetRotation implements Command, Serializable {
    private String target;
    private Quaternion rotation;

    public SetRotation(String target, Quaternion rotation) {
            this.target = target;
            this.rotation = rotation;
        }

    @Override
    public void execute(Map<String, GameObject> gameObjects) {
        gameObjects.get(target).setRotation(rotation);
    }
}
