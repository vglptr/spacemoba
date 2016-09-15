package shared.commands;

import java.io.Serializable;
import java.util.Map;

import shared.Point;
import shared.gameobjects.GameObject;

@SuppressWarnings("serial")
public class SetPosition extends Command implements Serializable {
    private String target;
    private Point position;

    public SetPosition(String target, Point position) {
        this.target = target;
        this.position = position;
    }
    
    public String getTarget() {
        return target;
    }
    
    public Point getPosition() {
        return position;
    }
    
}
