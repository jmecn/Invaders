package mygame;

import com.simsilica.es.EntityComponent;

public class Attack implements EntityComponent {

    private final double power;

    public Attack(double power) {
        this.power = power;
    }

    public double getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Attack[" + power + "]";
    }
}