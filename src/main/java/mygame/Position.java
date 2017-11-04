package mygame;

import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

public class Position implements EntityComponent {

    private final Vector3f location;
    private final Vector3f rotation;

    public Position(Vector3f location, Vector3f rotation) {
        this.location = location;
        this.rotation = rotation;
    }

    public Position(Vector3f location) {
        this (location, new Vector3f(0, 0, 0));
    }

    public Vector3f getLocation() {
        return location;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + location + ", " + rotation + "]";
    }
}