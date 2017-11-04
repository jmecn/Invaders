package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

public class GameAppState extends AbstractAppState {

    private EntityData ed;
    private SimpleApplication app;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {

        this.app = (SimpleApplication) app;
        this.app.setPauseOnLostFocus(true);
        this.app.setDisplayStatView(true);

        this.ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();

        // 创建太空船实体
        EntityId ship = ed.createEntity();
        this.ed.setComponents(ship,
                new Position(new Vector3f(0, -20, 0)),
                new Model(Model.SpaceShip));

        // 创建入侵者实体
        for (int x = -20; x < 20; x += 4) {
            for (int y = 0; y < 20; y += 4) {
                EntityId invader = ed.createEntity();
                this.ed.setComponents(invader,
                		new Defense(2),
                		new CollisionShape(1),
                        new Position(new Vector3f(x, y, 0)),
                        new Model(Model.BasicInvader));
            }
        }
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void update(float tpf) {
    }

}