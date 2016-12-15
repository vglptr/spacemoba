package shared.gameobjects;

import java.io.Serializable;

import shared.Point;

@SuppressWarnings("serial")
public class Ship extends GameObject implements Serializable {  
    public Ship() {
        position = new Point(0, 0);
    }

    public Ship(Point position) {
        this.position = position;
    }

   
}
