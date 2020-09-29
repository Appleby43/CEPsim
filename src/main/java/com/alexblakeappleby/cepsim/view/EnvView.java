package com.alexblakeappleby.cepsim.view;

import com.alexblakeappleby.cepsim.model.env.Environment;
import com.alexblakeappleby.cepsim.model.env.Tile;
import javafx.scene.Group;
import javafx.scene.layout.HBox;

/**
 * View for Environment
 * @see Environment
 */
public class EnvView extends Group {
    private static final int SPACING = 3;
    private Environment environment;

    public EnvView(Environment environment) {
        this.environment = environment;

        for (int i = 0; i < environment.size; i++) {
            HBox hBox = new HBox(SPACING);
            for (int j = 0; j < environment.size; j++) {
                Tile t = environment.getTile(i, j);
                TileView tv = new TileView(t);
                hBox.getChildren().add(tv);
            }
            getChildren().add(hBox);
            hBox.setLayoutY(-i * (TileView.RADIUS * 2) + SPACING);
            hBox.setLayoutX(-i * TileView.RADIUS);
        }
    }
}
