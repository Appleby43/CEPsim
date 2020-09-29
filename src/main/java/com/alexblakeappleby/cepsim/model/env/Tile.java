package com.alexblakeappleby.cepsim.model.env;

import com.alexblakeappleby.cepsim.model.species.Organism;
import com.alexblakeappleby.cepsim.model.species.Species;
import com.alexblakeappleby.cepsim.util.UpdateEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * A tile is one 'microhabitat' that makes up a larger environment.
 * Each tile can only contain one organism.
 */
public class Tile {
    private State state = State.FREE;
    private Organism inhabitant = null;
    private Set<Tile> neighbors = new HashSet<>();

    private UpdateEvent updateEvent;

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

    public Organism getInhabitant(){
        return inhabitant;
    }

    public void setUpdateEvent(UpdateEvent updateEvent){
        this.updateEvent = updateEvent;
    }

    private void update(){
        if(updateEvent != null){
            updateEvent.onUpdate();
        }
    }

    public Set<Tile> getNeighbors (){
        return neighbors;
    }

    public boolean isInhabited(){
        return (inhabitant != null);
    }

}
