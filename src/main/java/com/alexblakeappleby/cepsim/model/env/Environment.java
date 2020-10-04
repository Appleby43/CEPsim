package com.alexblakeappleby.cepsim.model.env;

import com.alexblakeappleby.cepsim.model.species.Organism;
import com.alexblakeappleby.cepsim.model.species.Species;

import java.util.*;

public class Environment {

    /**
     * The one-dimensional size of the environment.
     * All environments are 'square', so size is the amount of Tiles on a given side.
     */
    public final int size;
    public final int tileCount;
    private int time = 0;

    //tile is assembled as (y, x) to make construction more intuitive
    //when indexing, use getTile(x, y) for readability
    private final Tile[][] tiles;
    public final Species[] species;

    public int getTime() {
        return time;
    }

    public enum Mode {
        SQUARE,
        TOROIDAL
    }

    public Environment(int size, Mode mode, Species... species) {
        this.size = size;
        this.species = species;
        tileCount = size * size;

        tiles = new Tile[size][size];

        //create tiles, starting from the bottom of the grid
        //along the x axis and moving horizontally
        //as we progress, add tiles to existing neighbors
        for (int y = 0; y < tiles.length; y++) {
            Tile[] row = tiles[y];
            for (int x = 0; x < row.length; x++) {
                Tile t = new Tile(x,y);
                row[x] = t;

                //add lower neighbors and vice versa
                if(y != 0){
                    //tile (x, y-1) and (x-1, y-1) are neighbor pairs

                    //edge case where x-1 doesn't exist
                    if(x != 0){
                        makeNeighborPair(t, getTile(x - 1, y - 1));
                    }
                    makeNeighborPair(t, getTile(x, y - 1));
                }

                //add lateral neighbors
                if(x != 0){
                    makeNeighborPair(t, getTile(x - 1, y));
                }
            }
        }

        if(mode == Mode.TOROIDAL){
            int maxIndex = size - 1;
            for (int i = 0; i < size; i++) {
                //create neighbor pairs between top and bottom edges
                makeNeighborPair(getTile(i, 0), getTile(i, maxIndex));

                if(i != maxIndex){
                    //add the tile up and to the left for top tiles (vice versa)
                    makeNeighborPair(getTile(i, 0), getTile(i + 1, maxIndex));
                }

                //create neighbor pairs between left and right edges
                makeNeighborPair(getTile(0, i), getTile(maxIndex, i));

                if(i != 0){
                    //add the tile down and to the right for the left tiles(vice versa)
                    makeNeighborPair(getTile(0, i), getTile(maxIndex,  i - 1));
                }
            }
        }
    }

    public Environment(int size, Species... species) {
        this(size, Mode.SQUARE, species);
    }

    /**
     * Populates the environment with a random distribution of species
     * 50 percent of the grid will be empty. The other 50 percent will be populated by
     * an equal distribution of the specified species.
     */
    public void populateRandomly(){
        int emptyTiles = (int) Math.ceil(tileCount / 2.0);
        //integer division floors rounding, so emptyTiles may be larger than specified
        int speciesPer = (tileCount - emptyTiles) / 2;

        //create a list of all tiles
        List<Tile> allTiles = new ArrayList<>();
        for (Tile[] arr : tiles) {
            allTiles.addAll(Arrays.asList(arr));
        }

        //shuffle the list of all tiles
        Collections.shuffle(allTiles);

        //index the shuffled array as many times as needed
        int index = 0;
        for (int i = 0; i < speciesPer; i++) {
            for (Species s : species) {
                Tile t = allTiles.get(index);
                t.inhabit(s);
                index++;
            }
        }
    }
    
    public void progress(){
        time++;

        for (Species s : Species.getSpecies()) {
            for (Organism o : s.getOrganisms()) {
                o.progress();
            }
            s.progress();
        }

        for (Tile[] arr : tiles) {
            for (Tile t : arr) {
                t.progress();
            }
        }
    }

    public void populateCorners() {
        Species s1 = species[0];
        Species s2 = species[1];

        getTile(0, size - 1).inhabit(s1);
        getTile(size - 1, 0).inhabit(s2);
    }

    private void makeNeighborPair(Tile t0, Tile t1) {
        t0.addNeighbor(t1);
        t1.addNeighbor(t0);
    }

    public Tile getTile(int x, int y){
        return tiles[y][x];
    }

}
