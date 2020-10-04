package com.alexblakeappleby.cepsim.model.env;

import com.alexblakeappleby.cepsim.model.species.Species;
import com.alexblakeappleby.cepsim.util.Util;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    @Test
    public void testPopulateEnvironment() {
        Species s1 = new Species(1), s2 = new Species(1);

        Environment environment = new Environment(10, s1, s2);

        assertEquals(environment.tileCount, 100);


        environment.populateRandomly();

        assertEquals(s1.populationCount(), 25);
        assertEquals(s2.populationCount(), 25);
    }

    @Test
    public void testTorus() {
        Environment environment = new Environment(6, Environment.Mode.TOROIDAL);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(6, environment.getTile(i, j).getNeighbors().size());
            }
        }
    }

    @Test
    public void testContinuity() {
        //make the test deterministic
        Util.setRandom();

        Environment e = new Environment(50, new Species(0.5), new Species(0.5));
        e.populateRandomly();

        //do this a few times to fully populate the grid ig
        e.progress();
        e.progress();
        e.progress();

        Map<Tile, Tile.State> map = new HashMap<>();

        for (int i = 0; i < e.size; i++) {
            for (int j = 0; j < e.size; j++) {
                Tile t = e.getTile(i,j);

                map.put(t, t.getState());
            }
        }

        //after progressing one time val, no tiles should have the same state as previous.
        e.progress();

        for (int i = 0; i < e.size; i++) {
            for (int j = 0; j < e.size; j++) {
                Tile t = e.getTile(i,j);
                Tile.State previousState = map.get(t);

                assertNotEquals(previousState, t.getState());
            }
        }

    }
}