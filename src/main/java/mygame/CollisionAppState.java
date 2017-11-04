package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntitySet;


public class CollisionAppState extends AbstractAppState {

    private SimpleApplication app;
    private EntityData ed;
    private EntitySet attackingParts;
    private EntitySet defendingParts;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        this.app = (SimpleApplication) app;
        this.ed = this.app.getStateManager().getState(EntityDataState.class).getEntityData();

        attackingParts = ed.getEntities(Attack.class, CollisionShape.class, Position.class);
        defendingParts = ed.getEntities(Defense.class, CollisionShape.class, Position.class);
    }

    @Override
    public void update(float tpf) {
        attackingParts.applyChanges();
        defendingParts.applyChanges();
        attackingParts.stream().forEach((attackingPart) -> {
            defendingParts.stream().forEach((defendingPart) -> {
                if (hasCollides(attackingPart, defendingPart)) {
                    Attack attack = attackingPart.get(Attack.class);
                    Defense defense = defendingPart.get(Defense.class);
                    attackingPart.set(new Attack(attack.getPower() - defense.getPower()));
                    defendingPart.set(new Defense(defense.getPower() - attack.getPower()));
                    if (attackingPart.get(Attack.class).getPower() <=0 ) {
                        ed.removeEntity(attackingPart.getId());
                    }
                    if (defendingPart.get(Defense.class).getPower() <= 0) {
                        ed.removeEntity(defendingPart.getId());
                    }
                }
            });
        });
    }

    private boolean hasCollides(Entity e1, Entity e2) {
        CollisionShape e1Shape = e1.get(CollisionShape.class);
        CollisionShape e2Shape = e2.get(CollisionShape.class);
        Position e1Pos = e1.get(Position.class);
        Position e2Pos = e2.get(Position.class);

        float threshold = e1Shape.getRadius() + e2Shape.getRadius();
        threshold *= threshold;
        float distance = e1Pos.getLocation().distanceSquared(e2Pos.getLocation());

        return distance < threshold;
    }

    @Override
    public void cleanup() {
        super.cleanup();
        attackingParts.release();
        attackingParts = null;
        defendingParts.release();
        defendingParts = null;
    }
}