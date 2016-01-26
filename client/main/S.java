package client.main;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;

public class S {
    private static InputManager inputManager;
    private static AssetManager assetManager;

    private S() {
    }

    public static InputManager getInputManager() {
        return inputManager;
    }

    public static void setInputManager(InputManager inputManager) {
        S.inputManager = inputManager;
    }

    public static AssetManager getAssetManager() {
        return assetManager;
    }

    public static void setAssetManager(AssetManager assetManager) {
        S.assetManager = assetManager;
    }
}
