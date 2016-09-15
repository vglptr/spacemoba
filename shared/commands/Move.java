package shared.commands;

import java.io.Serializable;

import shared.Point;

@SuppressWarnings("serial")
public class Move extends Command implements Serializable {
    private String target;
    private Point delta;

    public Move(String target, Point delta) {
        this.target = target;
        this.delta = delta;
    }

    public String getTarget() {
        return target;
    }
    
    public Point getDelta() {
        return delta;
    }



    
}
