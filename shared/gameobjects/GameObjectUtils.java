package shared.gameobjects;

import java.util.HashMap;

public class GameObjectUtils {
	public static HashMap<String, GameObject> getGameObjectsContains(final HashMap<String, GameObject> gameObjects,
			String filter) {
		HashMap<String, GameObject> filteredGameObjects = new HashMap<>();
		for (String gameObjectKey : gameObjects.keySet()) {
			if (gameObjectKey.contains(filter)) {
				filteredGameObjects.put(gameObjectKey, gameObjects.get(gameObjectKey));
			}
		}
		return filteredGameObjects;
	}
}
