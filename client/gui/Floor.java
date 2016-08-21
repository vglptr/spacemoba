package client.gui;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

public class Floor {
    private Geometry geom;
    public Floor(AssetManager assetManager) {
        Mesh floorMesh = new Mesh();
        
        Vector3f [] vertices = new Vector3f[4];
        vertices[0] = new Vector3f(0.0f, 0.0f, 0.0f);
        vertices[1] = new Vector3f(100.0f, 0.0f, 0.0f);
        vertices[2] = new Vector3f(0.0f, 100.0f, 0.0f);
        vertices[3] = new Vector3f(100.0f, 100.0f, 0.0f);
        
        Vector2f[] textureCoords = new Vector2f[4];
        textureCoords[0] = new Vector2f(0,0);
        textureCoords[1] = new Vector2f(1,0);
        textureCoords[2] = new Vector2f(0,1);
        textureCoords[3] = new Vector2f(1,1);
        
        
        int [] indexes = { 2,0,1, 1,3,2 };
        
        floorMesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        floorMesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(textureCoords));
        floorMesh.setBuffer(Type.Index,    3, BufferUtils.createIntBuffer(indexes));
        floorMesh.updateBound();
        
        geom = new Geometry("floor", floorMesh); 
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Gray);
        geom.setMaterial(mat);
    }
    
    public Geometry getGeometry() {
        return geom;
    }
}
