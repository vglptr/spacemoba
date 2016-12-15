package client.gui;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

public class DestinationMarkerController extends AbstractControl {
    float scale = 1.0f;
    boolean growing = true;

    @Override
    protected void controlUpdate(float tpf) {
        if(growing) {
            scale += tpf * 2;
            if(scale > 1.5) {
                growing = false;
            }
        }
        if(!growing) {
            scale = 0;
            growing = true;
        }
        spatial.setLocalScale(scale);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
}
