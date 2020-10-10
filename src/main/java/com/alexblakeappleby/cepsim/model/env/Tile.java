package com.alexblakeappleby.cepsim.model.env;

import com.alexblakeappleby.cepsim.model.species.Organism;
import com.alexblakeappleby.cepsim.model.species.Species;
import com.alexblakeappleby.cepsim.util.Util;

import java.util.*;

/**
 * A tile is one 'microhabitat' that makes up a larger environment.
 * Each tile can only contain one organism.
 */
public class Tile extends ProgressableElement{
    private State state = State.FREE;
    private Organism inhabitant = null;
    private Set<Tile> neighbors = new HashSet<>();

    private ProgressEvent progressEvent;
    private List<Species> contestingSpecies = new ArrayList<>();

    public final int x, y;

    public enum State {
        FREE,
        OCCUPIED,
        REGENERATING,
    }

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void addNeighbor(Tile neighbor){
        if(neighbors.size() >= 6){
            throw new Error("this tile has too many neighbors. Check to make sure the environment was created correctly.");
        }

        neighbors.add(neighbor);
    }

    public void inhabit(Species species){
        inhabitant = new Organism(species, this);
        state = State.OCCUPIED;
        update();
    }

    private void uninhabit(State state){
        this.state = state;
        inhabitant = null;
        update();
    }

    public void contest(Organism o){
        if (!contestingSpecies.contains(o.species)){
            contestingSpecies.add(o.species);
        }
    }

    /**
     * Called when time progresses one unit.
     * contestingSpecies should be populated upon invocation.
     */
    public void internalProgress() {
        switch (state){
            case FREE, REGENERATING -> {
                if(contestingSpecies.size() == 0) {
                    uninhabit(State.FREE);
                    break;
                }

                /*
                If the tile is free or regenerating, it is eligible to be occupied next time unit.
                Determine the species that occupies this microhabitat.
                The odds of a given species occupying a microhabitat is equal to
                the the proportion of that species' strength relevant to the sum of
                all contesting species' strengths
                */
                double competitiveSum = 0;

                for (Species s : contestingSpecies) {
                    competitiveSum += s.strength;
                }

                //find a number between 0 and competitiveSum
                double competitiveVal = Util.getRandom().nextDouble() * competitiveSum;

                //determine what 'range' this random number falls into relative to the competitive sum
                double runningSum = 0;
                for (Species s : contestingSpecies) {
                    if(competitiveVal <= s.strength + runningSum){
                        //this is the winning species
                        inhabit(s);
                        return;
                    }
                    runningSum += s.strength;
                }
            }
            case OCCUPIED -> {
                uninhabit(State.FREE);
            }
        }
        contestingSpecies.clear();
    }

    public Organism getInhabitant(){
        return inhabitant;
    }

    public State getState(){
        return state;
    }

    private void update(){
        if(progressEvent != null) progressEvent.onProgress();
    }

    public Set<Tile> getNeighbors (){
        return neighbors;
    }

    public boolean isInhabited(){
        return (inhabitant != null);
    }

}
