package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VisualAppState extends AbstractAppState {
    private SimpleApplication app;
    private EntityData ed;
    private EntitySet entities;
    private final Map<EntityId, Spatial> models;
    private ModelFactory modelFactory;

    public VisualAppState() {
        this.models = new HashMap<EntityId, Spatial>();
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;

        // 筛选用于显示的实体
        ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();
        entities = ed.getEntities(Position.class, Model.class);

        // 初始化摄像机，从Z轴正上方往下看。
        app.getCamera().lookAt(Vector3f.UNIT_Z, Vector3f.UNIT_Y);
        app.getCamera().setLocation(new Vector3f(0, 0, 60));

        // 添加定向光源
        DirectionalLight light = new DirectionalLight();
        light.setDirection(new Vector3f(1, 1, -1));
        this.app.getRootNode().addLight(light);

        // 加载太空船模型
        modelFactory = new ModelFactory(this.app.getAssetManager());
    }

    @Override
    public void cleanup() {
        entities.release();
        entities = null;
    }

    @Override
    public void update(float tpf) {
        if (entities.applyChanges()) {
            removeModels(entities.getRemovedEntities());
            addModels(entities.getAddedEntities());
            updateModels(entities.getChangedEntities());
        }
    }

    private void removeModels(Set<Entity> entities) {
        for (Entity e : entities) {
            Spatial s = models.remove(e.getId());
            s.removeFromParent();
        }
    }

    private void addModels(Set<Entity> entities) {
        for (Entity e : entities) {
            Spatial s = createVisual(e);
            models.put(e.getId(), s);
            updateModelSpatial(e, s);
            this.app.getRootNode().attachChild(s);
        }
    }

    private void updateModels(Set<Entity> entities) {
        for (Entity e : entities) {
            Spatial s = models.get(e.getId());
            updateModelSpatial(e, s);
        }
    }

    private void updateModelSpatial(Entity e, Spatial s) {
        Position p = e.get(Position.class);
        s.setLocalTranslation(p.getLocation());
    }

    private Spatial createVisual(Entity e) {
        Model model = e.get(Model.class);
        return modelFactory.create(model.getName());
    }
}