package client;

import java.util.Map;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.system.AppSettings;

import shared.gameobjects.GameObject;

public class S {
    public static Map<String, GameObject> gameObjects;
    public static Map<String, String> gameProperties;
    public static InputManager inputManager;
    public static Camera cam;
    public static AssetManager assetManager;
    public static AppSettings appSettings;
    
    private S() {
    }
}
