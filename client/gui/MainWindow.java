package client.gui;

import java.util.logging.Logger;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import client.GameClient;
import client.S;
import shared.gameobjects.GameObject;
import shared.gameobjects.Ship;

public class MainWindow extends SimpleApplication {
    private GameClient parent;
    private final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
    private ShipModel shipModel;

    @Override
    public void simpleInitApp() {
        setDisplayStatView(false);
        setPauseOnLostFocus(false);
        S.inputManager = inputManager;
        S.inputManager.clearMappings();
        S.assetManager = assetManager;
        S.appSettings = settings;
        S.cam = cam;
        S.cam.setLocation(new Vector3f(0, 0, 50));
        S.cam.lookAt(new Vector3f(0, 0, 0), new Vector3f(0, 1, 0));
        // cam.setFrustumPerspective(45, cam.getWidth() / cam.getHeight(), 1,
        // 100);
        mouseInput.setCursorVisible(true);
        flyCam.setEnabled(false);
        
        Floor floor = new Floor(S.assetManager);
        rootNode.attachChild(floor.getGeometry());
        
        Material mat = new Material(S.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        
        shipModel = new ShipModel();
        rootNode.attachChild(shipModel.getGeometry());
        ShipController shipController = new ShipController(floor.getGeometry(), rootNode, S.appSettings);
        shipModel.getGeometry().addControl(shipController);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0.1f,0.1f,-1.0f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
    }
    
    @Override
    public void destroy() {
    super.destroy();
        parent.close();
    }
    
    public void setParent(GameClient parent) {
        this.parent = parent;
    }


    @Override
    public void simpleUpdate(float tpf) {
//        if (S.gameObjects != null) {
//            for (GameObject gameObject : S.gameObjects.values()) {
//                if (gameObject instanceof Ship) {
//                    //Ship ship = (Ship) gameObject;
//                    //shipModel.getGeometry().setLocalTranslation(ship.getPosition().x, ship.getPosition().y, 0f);
//                }
//            }
//        }
    }

}