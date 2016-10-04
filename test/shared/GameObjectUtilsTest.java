package test.shared;

import static org.junit.Assert.assertTrue;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import shared.gameobjects.GameObject;
import shared.gameobjects.GameObjectUtils;
import shared.gameobjects.Ship;

public class GameObjectUtilsTest {
    @Test
    public void canSelectAllShipsButOne() {
        Map<String, GameObject> gameObjects = new HashMap<>();
        Entry<String, GameObject> ship0 = new AbstractMap.SimpleEntry<String, GameObject>("ship0", new Ship());
        Entry<String, GameObject> ship1 = new AbstractMap.SimpleEntry<String, GameObject>("ship1", new Ship());
        Entry<String, GameObject> ship2 = new AbstractMap.SimpleEntry<String, GameObject>("ship2", new Ship());
        Entry<String, GameObject> ship3 = new AbstractMap.SimpleEntry<String, GameObject>("ship3", new Ship());
        Entry<String, GameObject> missile0 = new AbstractMap.SimpleEntry<String, GameObject>("missile0", new Ship());
        Entry<String, GameObject> whatshisface0 = new AbstractMap.SimpleEntry<String, GameObject>("whatshisface0", new Ship());
        
        gameObjects.entrySet().add(ship0);
        gameObjects.entrySet().add(ship1);
        gameObjects.entrySet().add(ship2);
        gameObjects.entrySet().add(ship3);
        gameObjects.entrySet().add(missile0);
        gameObjects.entrySet().add(whatshisface0);
        
        Map<String, GameObject> gameObjectsFiltered = GameObjectUtils.except("ship", 0, gameObjects);
        
        
        Map<String, GameObject> gameObjectsExpected = new HashMap<>();
        gameObjectsExpected.put("ship1", new Ship());
        gameObjectsExpected.put("ship2", new Ship());
        gameObjectsExpected.put("ship3", new Ship());
        
        assertTrue("filtered map is not the same as expected", gameObjectsFiltered.equals(gameObjectsExpected));
        
    }
}
