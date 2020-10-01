package com.alexblakeappleby.cepsim.model.species;

import java.util.ArrayList;
import java.util.List;

public class Species {
    private static List<Species> species = new ArrayList<>();

    /**
     * int identifier for each species
     */
    public final int id;
    public final double strength;
    private List<Organism> organisms = new ArrayList<>();

    /**
     * @param strength the probabilistic 'competitiveness' of a species. Should be 0 - 1.
     */
    public Species(double strength) {
        this.strength = strength;
        id = species.size();
        species.add(this);
    }

    public int populationCount(){
        return organisms.size();
    }

    public static List<Species> getSpecies(){
        return species;
    }

    public List<Organism> getOrganisms(){
        return organisms;
    }

    public void progress(){
        organisms.clear();
    }

    void addOrganism(Organism organism){
        organisms.add(organism);
    }

}
