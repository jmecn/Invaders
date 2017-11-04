package mygame;

import com.simsilica.es.EntityComponent;

public class Decay implements EntityComponent {
    private final long start;
    private final long delta;

    public Decay( long deltaMillis ) {
        this.start = System.nanoTime();
        this.delta = deltaMillis * 1000000;
    }

    public double getPercent() {
        long time = System.nanoTime();
        return (double)(time - start)/delta;
    }

    @Override
    public String toString() {
        return "Decay[" + (delta/1000000.0) + " ms]";
    }
}