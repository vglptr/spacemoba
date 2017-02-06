package shared.commands;

import shared.gameobjects.GameObject;

import java.util.Map;

public class BasicAttack implements Command {
    private GameObject target;
    
    public BasicAttack(GameObject target) {
        this.target = target;
    }

    @Override
    public void execute(Map<String, GameObject> gameObjects) {

    }
}
