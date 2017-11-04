package mygame;

import com.simsilica.es.EntityComponent;

public class Speed implements EntityComponent {

    private final float speed;

    public Speed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Speed[" + speed + "]";
    }
}