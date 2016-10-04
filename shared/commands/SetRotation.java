package shared.commands;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SetRotation extends Command implements Serializable {
    private String target;
    private int rotation;

    public SetRotation(String target, int rotation) {
        this.target = target;
        this.rotation = rotation;
    }
    
    public String getTarget() {
        return target;
    }
    
    public int getRotation() {
        return rotation;
    }
    
}
