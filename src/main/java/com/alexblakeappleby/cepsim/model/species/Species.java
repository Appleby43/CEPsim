package com.alexblakeappleby.cepsim.model.species;

import java.util.ArrayList;
import java.util.List;

public class Species {
    private static List<Species> species = new ArrayList<>();
    private final int id;
    private final double strength;

    /**
     * @param strength the probabilistic 'competitiveness' of a species. Should be 0 - 1.
     */
    public Species(double strength) {
        this.strength = strength;
        id = species.size();
        species.add(this);
    }
}
