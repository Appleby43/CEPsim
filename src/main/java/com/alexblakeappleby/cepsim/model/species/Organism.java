package com.alexblakeappleby.cepsim.model.species;

import com.alexblakeappleby.cepsim.model.env.Tile;

public class Organism {
    public final Species species;
    public final Tile habitat;

    public Organism(Species species, Tile habitat) {
        this.species = species;
        this.habitat = habitat;
    }
}
