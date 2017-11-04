package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntitySet;

public class DecayAppState extends AbstractAppState {
    private SimpleApplication app;
    private EntityData ed;
    private EntitySet decays;

    public DecayAppState() {
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();
        this.decays = this.ed.getEntities(Decay.class);
    }

    @Override
    public void cleanup() {
        this.decays.release();
        this.decays = null;
    }

    @Override
    public void update(float tpf) {
        decays.applyChanges();
        decays.stream().forEach((e) -> {
            Decay decay = e.get(Decay.class);
            if (decay.getPercent() >= 1) {
                ed.removeEntity(e.getId());
            }
        });

    }
}