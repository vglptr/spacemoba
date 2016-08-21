package client.gui;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import com.jme3.system.AppSettings;

import client.S;

public class ShipController extends AbstractControl implements ActionListener, AnalogListener{
    private boolean mouseRightDown;
    private Vector3f destination = new Vector3f(0.0f, 0.0f, 0.0f);
    private DestinationMarker destMark;
    private Geometry floor;
    private float speed = 4;
    private Vector3f newLocation = new Vector3f();
    private Vector3f origin;
    private Vector3f direction;
    private Ray ray = new Ray(Vector3f.ZERO, Vector3f.ZERO);
    private Vector3f up = new Vector3f(0.0f, 1.0f, 0.0f);
    
    public ShipController(Geometry floor, Node rootNode, AppSettings settings) {
       
        this.floor = floor;
        
        S.inputManager.addMapping("mouseRightPressed", new Trigger[]{new MouseButtonTrigger(MouseInput.BUTTON_RIGHT)});
        S.inputManager.addMapping("mouseMoved", new Trigger[]{new MouseAxisTrigger(MouseInput.AXIS_X, true), new MouseAxisTrigger(MouseInput.AXIS_X, false)});        
        S.inputManager.addMapping("wheelUp", new Trigger[]{new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false)});
        S.inputManager.addMapping("wheelDown", new Trigger[]{new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true)});
        S.inputManager.addMapping("spacePressed", new Trigger[]{new KeyTrigger(KeyInput.KEY_SPACE)});
        
        S.inputManager.addListener(this, new String[]{"mouseRightPressed"});
        S.inputManager.addListener(this, new String[]{"mouseMoved"});
        S.inputManager.addListener(this, new String[]{"wheelUp"});
        S.inputManager.addListener(this, new String[]{"wheelDown"});
        S.inputManager.addListener(this, new String[]{"spacePressed"});
        destMark = new DestinationMarker(S.assetManager, rootNode);
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        if(name.equals("mouseMoved")) {
                if(mouseRightDown) {
                    setShipDestination();
                }
        }
        if(name.equals("wheelUp")) {
            S.cam.setLocation(S.cam.getLocation().subtract(0, 0, value * 5));
        }
        if(name.equals("wheelDown")) {
            S.cam.setLocation(S.cam.getLocation().add(0, 0, value * 5));
        }
        if(name.equals("spacePressed")) {
            newLocation.x = spatial.getLocalTranslation().x;
            newLocation.y = spatial.getLocalTranslation().y;
            newLocation.z = S.cam.getLocation().z;
            S.cam.setLocation(newLocation);
        }
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if(name.equals("mouseRightPressed")) {
            mouseRightDown = !mouseRightDown;
            if(isPressed) {
                setShipDestination();
            }
        }
    }

    private void setShipDestination() {
        origin = S.cam.getWorldCoordinates(S.inputManager.getCursorPosition(), 0.1f);
        direction = S.cam.getWorldCoordinates(S.inputManager.getCursorPosition(), 0.2f);
        direction.subtractLocal(origin);
        ray.setOrigin(origin);
        ray.setDirection(direction);
        CollisionResults results = new CollisionResults();
        floor.collideWith(ray, results);
            if (results.size() > 0) {
                CollisionResult closest = results.getClosestCollision();
                destination = closest.getContactPoint();
            }
        destMark.setPos(destination);
        spatial.lookAt(destination, up);
//        Quaternion rotate180 = new Quaternion(); 
//        rotate180.fromAngleAxis(FastMath.PI , new Vector3f(0, 0, 1)); 
//        spatial.rotate(rotate180);
        
    }

    protected void controlUpdate(float tpf) {
        //ship moves towards destination
        if(spatial.getLocalTranslation().distance(destination) > 0.01) {
            spatial.move(destination.subtract(spatial.getLocalTranslation()).normalize().mult(tpf).mult(speed));
        }
        
        //mouse scroll
        if(S.inputManager.getCursorPosition().x == 0) {
            S.cam.setLocation(S.cam.getLocation().subtract(tpf * 10, 0, 0));
        }
        
        if(S.inputManager.getCursorPosition().x == S.appSettings.getWidth() - 1) {
            S.cam.setLocation(S.cam.getLocation().add(tpf * 10, 0, 0));
        }
        
        if(S.inputManager.getCursorPosition().y == S.appSettings.getHeight() - 1) {
            S.cam.setLocation(S.cam.getLocation().add(0, tpf * 10, 0));
        }
        
        if(S.inputManager.getCursorPosition().y == 0) {
            S.cam.setLocation(S.cam.getLocation().subtract(0, tpf * 10, 0));
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        // TODO Auto-generated method stub
        
    }

}
