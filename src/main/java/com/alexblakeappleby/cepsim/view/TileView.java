package com.alexblakeappleby.cepsim.view;

import com.alexblakeappleby.cepsim.model.env.Tile;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class TileView extends Polygon {
    private Tile tile;
    public static final double RADIUS = 3;
    private static final Color UNOCCUPIED_FILL = Color.GAINSBORO;

    /*protected 4 testing*/ static List<TileView> tileViews = new ArrayList<>();

    public TileView(Tile tile) {
        this.tile = tile;
        tileViews.add(this);

        tile.setUpdateEvent(this::onUpdate);

        getPoints().addAll(//starts at 0 radians, goes CCW around 360 degrees
                RADIUS * cos(PI / 6.0),  RADIUS * sin(PI / 6.0),

                0.0, RADIUS,

                RADIUS * cos(5 * PI / 6.0),  RADIUS * sin(5 * PI / 6.0),

                RADIUS * cos(7 * PI / 6.0),  RADIUS * sin(7 * PI / 6.0),

                0.0, -RADIUS,

                RADIUS * cos(11 * PI / 6.0),  RADIUS * sin(11 * PI / 6.0)

                );
        setFill(UNOCCUPIED_FILL);

        setOnMouseClicked(e -> {
            System.out.println(tile.x);
            System.out.println(tile.y);
            System.out.println(tile.getInhabitant());
        });
    }

    /*protected 4 testing*/ Tile getTile(){
        return tile;
    }

    private void onUpdate(){
        Tile.State state = tile.getState();
        Color fillColor;

        switch (state){
            case FREE, REGENERATING -> {
                fillColor = UNOCCUPIED_FILL;
            }
            case OCCUPIED -> {
                fillColor = ColorRegistry.getSpeciesColor(tile.getInhabitant().species.id);
            }
            default -> throw new IllegalStateException("Unexpected value: " + state);
        }
        
        setFill(fillColor);
    }
}
