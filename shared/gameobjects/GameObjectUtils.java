package shared.gameobjects;

import java.util.HashMap;

import client.S;

public class GameObjectUtils {
    public static HashMap<String, GameObject> getGameObjectsContains(String filter) {
        HashMap<String, GameObject> filteredGameObjects = new HashMap<>();
        for (String gameObjectKey : S.gameObjects.keySet()) {
            if (gameObjectKey.contains(filter)) {
                filteredGameObjects.put(gameObjectKey, S.gameObjects.get(gameObjectKey));
            }
        }
        return filteredGameObjects;
    }
}
