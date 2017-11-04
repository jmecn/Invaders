package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class VisualAppState extends AbstractAppState {
    private SimpleApplication app;
    private ModelFactory modelFactory;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;

        // 初始化摄像机，从Z轴正上方往下看。
        app.getCamera().lookAt(Vector3f.UNIT_Z, Vector3f.UNIT_Y);
        app.getCamera().setLocation(new Vector3f(0, 0, 60));

        // 添加定向光源
        DirectionalLight light = new DirectionalLight();
        light.setDirection(new Vector3f(1, 1, -1));
        this.app.getRootNode().addLight(light);

        // 加载太空船模型
        modelFactory = new ModelFactory(this.app.getAssetManager());
        Spatial myVisual = modelFactory.create("SpaceShip");
        this.app.getRootNode().attachChild(myVisual);
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void update(float tpf) {
    }
}