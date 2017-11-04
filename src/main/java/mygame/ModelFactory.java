package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class ModelFactory {
    private final AssetManager assetManager;

    public ModelFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Spatial create(String name) {
        Node visual = new Node("Visual");
        Node model = (Node) assetManager.loadModel("Models/" + name + ".j3o");
        visual.attachChild(model);
        return visual;
    }
}