package com.alexblakeappleby.cepsim.model.env;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class EnvironmentTest {

    @Test
    public void test1CellEnv() {
        Environment e = new Environment(1);

        assertEquals(1, e.tileCount);

        Tile t = e.getTile(0,0);

        assertEquals(0, t.getNeighbors().size());
    }

    @Test
    public void test9CellEnv() {
        Environment e = new Environment(3);

        assertEquals(9, e.tileCount);

        Tile middleTile = e.getTile(1,1);

        assertEquals(6, middleTile.getNeighbors().size());

        Set<Tile> targetSet = new HashSet<>();
        targetSet.add(e.getTile(0,0));
        targetSet.add(e.getTile(1,0));
        targetSet.add(e.getTile(2,1));
        targetSet.add(e.getTile(2,2));
        targetSet.add(e.getTile(1,2));
        targetSet.add(e.getTile(0,1));

        assertEquals(targetSet, middleTile.getNeighbors());

        Tile cornerTile1 = e.getTile(2,2);
        assertEquals(3, cornerTile1.getNeighbors().size());

        Tile cornerTile2 = e.getTile(2,0);
        assertEquals(2, cornerTile2.getNeighbors().size());
    }
}