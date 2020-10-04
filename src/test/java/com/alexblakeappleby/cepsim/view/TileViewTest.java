package com.alexblakeappleby.cepsim.view;

import com.alexblakeappleby.cepsim.model.env.Environment;
import com.alexblakeappleby.cepsim.model.env.Tile;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TileViewTest {

    @Test
    public void testNoDoubleTileViews() {
        Environment e = new Environment(50, Environment.Mode.TOROIDAL);
        EnvView envView = new EnvView(e);

        Set<Tile> usedTiles = new HashSet<>();

        TileView.tileViews.forEach(t -> {
            assertFalse(usedTiles.contains(t.getTile()));
            usedTiles.add(t.getTile());
        });

        assertEquals(e.tileCount, usedTiles.size());
    }
}