package shared;

import java.util.ArrayList;

import shared.gameobjects.GameObject;

public class Level {
	private static final Level INSTANCE = new Level();
	private ArrayList<GameObject> gameObjects;
	private int sizeX = 1000;
	private int sizeY = 1000;

	private Level() {
		gameObjects = new ArrayList<GameObject>();
	}
	
	public static Level getInstance() {
		return INSTANCE;
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

}
