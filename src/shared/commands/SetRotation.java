package shared.commands;

import java.io.Serializable;

import com.jme3.math.Quaternion;

@SuppressWarnings("serial")
public class SetRotation extends Command implements Serializable {
    private String target;
    private Quaternion rotation;

    public SetRotation(String target, Quaternion rotation) {
        this.target = target;
        this.rotation = rotation;
    }
    
    public String getTarget() {
        return target;
    }
    
    public Quaternion getRotation() {
        return rotation;
    }
    
}
