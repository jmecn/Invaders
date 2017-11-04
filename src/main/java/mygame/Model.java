package mygame;

import com.simsilica.es.EntityComponent;

public class Model implements EntityComponent {
    private final String name;
    public final static String SpaceShip = "SpaceShip";
    public final static String BasicInvader = "BasicInvader";

    public Model(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Model[" + name + "]";
    }
}