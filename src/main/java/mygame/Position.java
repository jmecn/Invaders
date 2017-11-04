package mygame;

import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

public class Position implements EntityComponent {

    private final Vector3f location;

    public Position(Vector3f location) {
        this.location = location;
    }

    public Vector3f getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + location + "]";
    }
}