package shared.commands;

import shared.gameobjects.GameObject;

public class BasicAttack extends Command {
    private GameObject target;
    
    public BasicAttack(GameObject target) {
        this.target = target;
    }

    public GameObject getTarget() {
        return target;
    }
}
