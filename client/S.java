package client;

import java.util.Map;

import shared.gameobjects.GameObject;

public class S {
    private static Map<String, GameObject> gameObjects;
    private static Map<String, String> gameProperties;

    private S() {
    }

    public static Map<String, GameObject> getGameObjects() {
        return gameObjects;
    }

    public static void setGameObjects(Map<String, GameObject> gameObjects) {
        S.gameObjects = gameObjects;
    }

    public static Map<String, String> getGameProperties() {
        return gameProperties;
    }

    public static void setGameProperties(Map<String, String> gameProperties) {
        S.gameProperties = gameProperties;
    }
}
