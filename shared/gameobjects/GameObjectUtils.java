package shared.gameobjects;

import java.util.HashMap;
import java.util.Map;

public class GameObjectUtils {
    /**
     * Returns all the GameObjects containing filterText except the one with id exceptId, like ship0
     * @param filterText text describing the "type" of the GameObject, like: ship
     * @param exceptId the GameObject's id which we are not interested in 
     * @param map GameObjects container
     * @return filtered GameObjects
     */
    public static Map<String, GameObject> except(String filterText, int exceptId, Map<String, GameObject> map) {
        Map<String, GameObject> filteredGameObjects = new HashMap<>();
        for (String gameObjectKey : map.keySet()) {
            if ((gameObjectKey.contains(filterText)) && (!gameObjectKey.contains(filterText + exceptId))) {
                filteredGameObjects.put(gameObjectKey, map.get(gameObjectKey));
            }
        }
        return filteredGameObjects;
    }
    
    public static Map<String, GameObject> select(String filterText, int clientId, Map<String, GameObject> map) {
        Map<String, GameObject> filteredGameObjects = new HashMap<String, GameObject>();
        for (String gameObjectKey : map.keySet()) {
            if (gameObjectKey.contains(filterText + clientId)) {
                filteredGameObjects.put(gameObjectKey, map.get(gameObjectKey));
            }
        }
        return filteredGameObjects;
    }
}
