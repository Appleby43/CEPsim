package com.alexblakeappleby.cepsim.model.env;

public class Environment {

    /**
     * The one-dimensional size of the environment.
     * All environments are 'square', so size is the amount of Tiles on a given side.
     */
    public final int size;

    private Tile[][] tiles;

    public Environment(int size) {
        this.size = size;

        tiles = new Tile[size][size];

    }
}
