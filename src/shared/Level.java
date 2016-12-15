package shared;

public class Level {
    private static final Level INSTANCE = new Level();
    private int sizeX = 1000;
    private int sizeY = 1000;

    private Level() {
    }

    public static Level getInstance() {
        return INSTANCE;
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
