package client.gui;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import client.main.S;
import shared.gameobjects.GameObject;
import shared.gameobjects.Ship;

/**
 * Sample 1 - how to get started with the most simple JME 3 application. Display
 * a blue 3D cube and view from all sides by moving the mouse and pressing the
 * WASD keys.
 */
public class MainWindow extends SimpleApplication {
	Geometry geom;

	@Override
	public void simpleInitApp() {
		setDisplayStatView(false);
		inputManager.clearMappings();
		cam.setLocation(new Vector3f(0, 50, 0));
		cam.lookAt(new Vector3f(0, 0, 0), new Vector3f(0, -1, -1));
		// cam.setFrustumPerspective(45, cam.getWidth() / cam.getHeight(), 1,
		// 100);
		mouseInput.setCursorVisible(true);
		flyCam.setEnabled(false);
		flyCam.setMoveSpeed(50);

		Box b = new Box(1, 1, 1); // create cube shape
		geom = new Geometry("Box", b); // create cube geometry from the shape
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); // create
																						// a
																						// simple
																						// material
		mat.setColor("Color", ColorRGBA.Blue); // set color of material to blue
		geom.setMaterial(mat); // set the cube's material
		rootNode.attachChild(geom); // make the cube appear in the scene
	}

	@Override
	public void simpleUpdate(float tpf) {
		if(S.getGameObjects() == null) {
			System.out.println("its null");
		}
		if (S.getGameObjects() != null) {
			System.out.println("its not null");
			for (GameObject gameObject : S.getGameObjects().values()) {
				if (gameObject instanceof Ship) {
					Ship ship = (Ship) gameObject;
					System.out.println("its a ship");
					geom.setLocalTranslation(ship.getPosition().x, ship.getPosition().y, 0f);
				}
			}
		}
	}

}