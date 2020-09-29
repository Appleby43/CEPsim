package com.alexblakeappleby.cepsim.view;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

//todo make the color registry not suck
public class ColorRegistry {
    private static final List<Color> colors = new ArrayList<>();
    static {
        colors.add(Color.BLUE);
        colors.add(Color.RED);
    }

    public static Color getSpeciesColor(int speciesId){
        Color retVal = colors.get(speciesId);
        if(retVal == null) throw new Error("the color registry still sucks, idiot!");
        return retVal;
    }
}
