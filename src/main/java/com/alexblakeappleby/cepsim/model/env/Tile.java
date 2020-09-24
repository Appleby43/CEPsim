package com.alexblakeappleby.cepsim.model.env;

import com.alexblakeappleby.cepsim.model.species.Organism;

/**
 * A tile is one 'microhabitat' that makes up a larger environment.
 * Each tile can only contain one organism.
 */
public class Tile {
    private State state = State.FREE;
    private Organism inhabitant = null;
    private Tile[] neighbors;

    public enum State {
        FREE,
        OCCUPIED,
        REGENERATING,
    }

    public Tile(Tile[] neighbors){
        this.neighbors = neighbors;
    }

    public boolean isInhabited(){
        return (inhabitant != null);
    }
}
