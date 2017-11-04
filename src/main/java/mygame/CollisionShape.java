package mygame;
import com.simsilica.es.EntityComponent;

public class CollisionShape implements EntityComponent {

    private final float radius;

    public CollisionShape(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Radius[" + radius + "]";
    }
}