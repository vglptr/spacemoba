package client.gui;

import java.util.HashMap;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import client.main.S;
import shared.gameobjects.GameObject;
import shared.gameobjects.Ship;

import static shared.gameobjects.GameObjectConstants.SHIP01;

public class MainWindow extends SimpleApplication {
	Geometry blue;
	HashMap<String, GameObject> gameObjects;

	public MainWindow(HashMap<String, GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	@Override
	public void simpleInitApp() {
		S.setAssetManager(assetManager);
		S.setInputManager(inputManager);
		inputManager.clearMappings();
		// flyCam.setEnabled(false);
		viewPort.setBackgroundColor(ColorRGBA.Gray);

		flyCam.setMoveSpeed(10);
		flyCam.setUpVector(Vector3f.UNIT_Z);
		mouseInput.setCursorVisible(true);
		setDisplayStatView(false);

		DirectionalLight sun = new DirectionalLight();
		sun.setDirection(new Vector3f(-0.5f, -0.5f, -0.5f).normalizeLocal());
		sun.setColor(new ColorRGBA(0.2f, 0.2f, 0.2f, 0.2f));

		DirectionalLight fillLight = new DirectionalLight();
		fillLight.setDirection(new Vector3f(1, 1, -1).normalizeLocal());
		fillLight.setColor(new ColorRGBA(0.2f, 0.2f, 0.2f, 0.2f));

		DirectionalLight fillLight2 = new DirectionalLight();
		fillLight2.setDirection(new Vector3f(-1, -1, 0).normalizeLocal());
		fillLight2.setColor(new ColorRGBA(0.2f, 0.2f, 0.2f, 0.2f));

		rootNode.addLight(sun);
		rootNode.addLight(fillLight);
		rootNode.addLight(fillLight2);

		Box box1 = new Box(1, 1, 1);
		blue = new Geometry("Box", box1);
		blue.setLocalTranslation(new Vector3f(1, -1, 1));
		Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat1.setColor("Color", ColorRGBA.Blue);
		blue.setMaterial(mat1);

		rootNode.attachChild(blue);
	}
	
	public void setGameObjects(HashMap<String, GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public void simpleUpdate(float tpf) {
		if (gameObjects != null) {
			System.out.println("size in gui update: " + gameObjects.size());
			
			Ship ship = (Ship) gameObjects.get(SHIP01);
			System.out.println(gameObjects.size());
			for (String s : gameObjects.keySet()) {
				System.out.println(s);
			}
			if (ship != null) {
				System.out.println(ship.getPosition());
				blue.setLocalTranslation(ship.getPosition().x, ship.getPosition().y, 0.0f);
			}
		}

	}

}
