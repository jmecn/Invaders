package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntitySet;

public class BulletAppState extends AbstractAppState {

    private SimpleApplication app;
    private EntityData ed;
    private EntitySet bullets;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        this.app = (SimpleApplication) app;
        this.ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();

        bullets = ed.getEntities(Model.class, Position.class, Speed.class);
    }

    @Override
    public void update(float tpf) {
        bullets.applyChanges();
        bullets.stream().forEach((e) -> {
            Position position = e.get(Position.class);
            Speed speed = e.get(Speed.class);
            e.set(new Position(position.getLocation().add(0, tpf * speed.getSpeed(), 0)));
        });
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }
}