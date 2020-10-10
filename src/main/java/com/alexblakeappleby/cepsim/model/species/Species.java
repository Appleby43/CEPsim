package com.alexblakeappleby.cepsim.model.species;

import com.alexblakeappleby.cepsim.model.env.ProgressableElement;

import java.util.ArrayList;
import java.util.List;

public class Species extends ProgressableElement {
    /**
     * int identifier for each species
     */
    public final int id;
    public final double strength;
    private List<Organism> organisms = new ArrayList<>();

    private static int count = 0;

    /**
     * @param strength the probabilistic 'competitiveness' of a species. Should be 0 - 1.
     */
    public Species(double strength) {
        this.strength = strength;
        id = count++;
    }

    public int populationCount(){
        return organisms.size();
    }

    public List<Organism> getOrganisms(){
        return organisms;
    }

    @Override
    protected void internalProgress(){
        organisms.clear();
    }

    void addOrganism(Organism organism){
        organisms.add(organism);
    }

}
