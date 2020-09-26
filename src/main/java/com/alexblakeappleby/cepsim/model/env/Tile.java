package com.alexblakeappleby.cepsim.model.env;

import com.alexblakeappleby.cepsim.model.species.Organism;

import java.util.ArrayList;
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

    private int x, y;

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

    public Set<Tile> getNeighbors (){
        return neighbors;
    }

    public boolean isInhabited(){
        return (inhabitant != null);
    }
}
