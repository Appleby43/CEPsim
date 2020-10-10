package com.alexblakeappleby.cepsim.model.species;

import com.alexblakeappleby.cepsim.model.env.ProgressableElement;
import com.alexblakeappleby.cepsim.model.env.Tile;

public class Organism extends ProgressableElement {
    public final Species species;
    public final Tile habitat;

    public Organism(Species species, Tile habitat) {
        this.species = species;
        this.habitat = habitat;
        species.addOrganism(this);
    }

    public void internalProgress() {
        for (Tile t : habitat.getNeighbors()) {
            t.contest(this);
        }
    }
}
