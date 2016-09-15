package shared.gameobjects;

import java.io.Serializable;

import shared.Point;

@SuppressWarnings("serial")
public abstract class GameObject implements Serializable {
    protected Point destination;
    protected Point position;
    protected float rotation;
    protected int id;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    /**
     * Gets angle in degrees. Measured from the positive side of axis x. (East
     * is 0, North is 90)
     * 
     * @return rotation in degrees
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * Sets angle in degrees. Measured from the positive side of axis x. (East
     * is 0, North is 90)
     * 
     * @param rotation
     *            in degrees
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * Moves the GameObject by the given amount
     * 
     * @param point
     *            the amount the GameObject will be moved further
     */
    public void move(Point point) {
        position.add(point);
    }
    
    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }
    
}
