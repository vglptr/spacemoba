package shared.gameobjects;

import java.io.Serializable;

import com.jme3.math.Quaternion;

import shared.Point;

@SuppressWarnings("serial")
public abstract class GameObject implements Serializable {
    protected Point destination;
    protected Point position;
    protected Quaternion rotation;
    protected int id;
    
    public GameObject() {
        position = new Point(0, 0);
        rotation = new Quaternion();
    }

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
     * Gets rotation in quaternion.
     * 
     * @return rotation
     */
    public Quaternion getRotation() {
        return rotation;
    }

    /**
     * Sets angle in quaternion.
     * 
     * @param rotation
     *           
     */
    public void setRotation(Quaternion rotation) {
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
