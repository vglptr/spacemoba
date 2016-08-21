package client.gui;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import client.S;

public class ShipModel {
    private Geometry geom;

    public ShipModel() {
        Box b = new Box(1, 1, 1);
        geom = new Geometry("Box", b);
        Material mat = new Material(S.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
    }

    public Geometry getGeometry() {
        return geom;
    }
}
