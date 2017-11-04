package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntitySet;
import com.simsilica.es.Filters;

public class ControlAppState extends AbstractAppState {

    private static final String MOVE_RIGHT = "MOVE_RIGHT";
    private static final String MOVE_LEFT = "MOVE_LEFT";

    private SimpleApplication app;
    private EntityData ed;
    private Vector3f position;
    private EntitySet ship;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {

        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;

        ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();
        ship = ed.getEntities(
                Filters.fieldEquals(Model.class, "name", Model.SpaceShip),
                Model.class,
                Position.class
        );

        this.position = new Vector3f(0, -20, 0);
        this.app.getInputManager().addMapping(MOVE_LEFT, new MouseAxisTrigger(MouseInput.AXIS_X, true));
        this.app.getInputManager().addMapping(MOVE_RIGHT, new MouseAxisTrigger(MouseInput.AXIS_X, false));

        this.app.getInputManager().addListener(analogListener, MOVE_LEFT, MOVE_RIGHT);
    }

    private final AnalogListener analogListener = (String name, float value, float tpf) -> {
        if (name.equals(MOVE_LEFT) || name.equals(MOVE_RIGHT)) {
            Vector2f mousePos = app.getInputManager().getCursorPosition();
            float x = FastMath.clamp((mousePos.getX() - app.getCamera().getWidth() / 2) * 0.05f, -22, 22);
            position = new Vector3f(x, -20, 0);
        }
    };

    @Override
    public void update(float tpf) {
        ship.applyChanges();
        ship.stream().findFirst().ifPresent(e -> {
            e.set(new Position(position));
        });
    }

    @Override
    public void cleanup() {
    }
}