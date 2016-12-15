package client.gui;

import java.util.HashMap;
import java.util.Map;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

import client.GameClient;
import client.S;
import shared.gameobjects.GameObject;
import shared.gameobjects.GameObjectUtils;

public class MainWindow extends SimpleApplication {
    private GameClient client;
    private Map<String, Geometry> geometries;
    private BitmapText mouseCoordinates;

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
        // cam.setFrustumPerspective(45, cam.getWidth() / cam.getHeight(), 1, 100);
        mouseInput.setCursorVisible(true);
        flyCam.setEnabled(false);

        Floor floor = new Floor(S.assetManager);
        rootNode.attachChild(floor.getGeometry());

        initShips();

        initShipController(floor);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0.1f, 0.1f, -1.0f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        debugInitAll();
    }

    private void initShipController(Floor floor) {
        ShipController shipController = new ShipController(floor.getGeometry(), rootNode, S.appSettings, client);
        geometries.get("ship" + S.clientId).addControl(shipController);
    }

    private void initShips() {
        for(int i = 0; i < 10; i++) {
            ShipModel shipModel = new ShipModel();
            rootNode.attachChild(shipModel.getGeometry());
            geometries.put("ship" + i, shipModel.getGeometry());
        }
    }

    private void debugInitAll() {
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        debugInitMouseCoordinates();
    }

    private void debugInitMouseCoordinates() {
        mouseCoordinates = new BitmapText(guiFont, false);
        mouseCoordinates.setSize(12);
        mouseCoordinates.setLocalTranslation(0, S.appSettings.getHeight(), 0);
        guiNode.attachChild(mouseCoordinates);
    }

    private void debugUpdateAll(){
        debugUpdateMouseCoordinates();
    }

    private void debugUpdateMouseCoordinates() {
        mouseCoordinates.setText("mouse x;y : " + (S.inputManager.getCursorPosition().x + ";" + S.inputManager.getCursorPosition().y));
    }

    @Override
    public void destroy() {
        super.destroy();
        client.close();
    }

    // control every object from here except the current client
    @Override
    public void simpleUpdate(float tpf) {
        if (S.gameObjects != null) {
            updateShips();
        }
        debugUpdateAll();
    }

    private void updateShips() {
        for(Map.Entry<String, GameObject> shipEntry : GameObjectUtils.except("ship", S.clientId, S.gameObjects).entrySet()) {
            geometries.get(shipEntry.getKey()).setLocalTranslation(
                    shipEntry.getValue().getPosition().x, shipEntry.getValue().getPosition().y, 0f);
            
            geometries.get(shipEntry.getKey()).setLocalRotation(shipEntry.getValue().getRotation());
        }
    }

    public void setClient(GameClient client) {
        this.client = client;
    }
}