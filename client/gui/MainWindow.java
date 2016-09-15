package client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

import client.GameClient;
import client.S;
import shared.gameobjects.GameObject;
import shared.gameobjects.GameObjectUtils;
import shared.gameobjects.Ship;

public class MainWindow extends SimpleApplication {
    private GameClient client;
    private ShipModel shipModel0;
    private ShipModel shipModel1;
    private Map<String, Geometry> geometries;

    @Override
    public void simpleInitApp() {
        geometries = new HashMap<>();
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

        shipModel0 = new ShipModel();
        ShipController shipController = new ShipController(floor.getGeometry(), rootNode, S.appSettings, client);
        rootNode.attachChild(shipModel0.getGeometry());
        shipModel0.getGeometry().addControl(shipController);
        geometries.put("ship0", shipModel0.getGeometry());

        shipModel1 = new ShipModel();
        rootNode.attachChild(shipModel1.getGeometry());
        geometries.put("ship1", shipModel1.getGeometry());

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0.1f, 0.1f, -1.0f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
    }

    @Override
    public void destroy() {
        super.destroy();
        client.close();
    }

    public void setClient(GameClient client) {
        this.client = client;
    }

    // control every object from here except the current client
    //TODO: something is wrong with second ship's control
    @Override
    public void simpleUpdate(float tpf) {
        if (S.gameObjects != null) {
            for(Map.Entry<String, GameObject> shipEntry : GameObjectUtils.filter("ship", S.clientId, S.gameObjects).entrySet()) {
                geometries.get(shipEntry.getKey()).setLocalTranslation(                    
                        shipEntry.getValue().getPosition().x, shipEntry.getValue().getPosition().y, 0f);
            }
        }
    }
}