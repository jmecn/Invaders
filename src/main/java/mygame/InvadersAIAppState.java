package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntitySet;
import com.simsilica.es.Filters;

public class InvadersAIAppState extends AbstractAppState {

    private SimpleApplication app;
    private EntityData ed;
    private EntitySet invaders;
    private float xDir;
    private float yDir;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        this.app = (SimpleApplication) app;
        this.ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();

        invaders = ed.getEntities(
                Filters.fieldEquals(Model.class, "name", Model.BasicInvader),
                Model.class,
                Position.class);
        xDir = 1f;
        yDir = -1f;
    }

    @Override
    public void update(float tpf) {
        invaders.applyChanges();
        wabbeling(tpf);
    }

    private void wabbeling(float tpf) {
        float xMin = 0;
        float xMax = 0;
        float yMin = 0;
        float yMax = 0;

        for (Entity e : invaders) {
            Vector3f location = e.get(Position.class).getLocation();
            if (location.getX() < xMin) {
                xMin = location.getX();
            }
            if (location.getX() > xMax) {
                xMax = location.getX();
            }
            if (location.getY() < yMin) {
                yMin = location.getY();
            }
            if (location.getY() > yMax) {
                yMax = location.getY();
            }            
            Vector3f rotation = e.get(Position.class).getRotation();
            rotation = rotation.add(0, tpf * FastMath.DEG_TO_RAD * 90, 0);
            if (rotation.y > FastMath.RAD_TO_DEG * 360) {
                rotation.setY(rotation.getY() - FastMath.DEG_TO_RAD * 360);
            }
            e.set(new Position(location.add(xDir * tpf * 2, yDir * tpf * 0.5f, 0), rotation));
        }
        if (xMax > 22) {
            xDir = -1;
        }
        if (xMin < -22) {
            xDir = 1;
        }
        if (yMax > 20) {
            yDir = -1;
        }
        if (yMin < 0) {
            yDir = 1;
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }
}