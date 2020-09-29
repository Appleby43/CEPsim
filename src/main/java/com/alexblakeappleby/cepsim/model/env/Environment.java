package com.alexblakeappleby.cepsim.model.env;

public class Environment {

    /**
     * The one-dimensional size of the environment.
     * All environments are 'square', so size is the amount of Tiles on a given side.
     */
    public final int size;
    public final int tileCount;

    //tile is assembled as (y, x) to make construction more intuitive
    //when indexing, use getTile(x, y) for readability
    private Tile[][] tiles;

    public Environment(int size) {
        this.size = size;
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
    }

    private void makeNeighborPair(Tile t0, Tile t1) {
        t0.addNeighbor(t1);
        t1.addNeighbor(t0);
    }

    public Tile getTile(int x, int y){
        return tiles[y][x];
    }

}
