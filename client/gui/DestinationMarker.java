package client.gui;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

public class DestinationMarker extends Geometry {
    Geometry sphereGeo;
    
    public DestinationMarker(AssetManager assetManager, Node rootNode) {
        Sphere sphereMesh = new Sphere(32,32, 0.5f);
        sphereGeo = new Geometry("Shiny rock", sphereMesh);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(0.7f, 0.7f, 1.0f, 1.0f));
        sphereGeo.setMaterial(mat);
        sphereGeo.addControl(new DestinationMarkerController());
        rootNode.attachChild(sphereGeo);
    }
    
    public void setPos(Vector3f pos) {
        sphereGeo.setLocalTranslation(pos);
    }

}

