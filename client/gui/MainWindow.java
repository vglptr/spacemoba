package client.gui;

import client.main.S;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class MainWindow extends SimpleApplication {

	@Override
	public void simpleInitApp() {
		S.setAssetManager(assetManager);
		S.setInputManager(inputManager);
		inputManager.clearMappings();
		flyCam.setEnabled(false);
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

		// AmbientLight al = new AmbientLight();
		// al.setColor(ColorRGBA.White.mult(0.2f));
		// rootNode.addLight(al);

		// rootNode.attachChild(box.getGeometry());
		rootNode.addLight(sun);
		rootNode.addLight(fillLight);
		rootNode.addLight(fillLight2);
		// rootNode.addLight(pointLight);
		System.out.println("MAINWINDOW: " + Thread.currentThread());
	}

}
