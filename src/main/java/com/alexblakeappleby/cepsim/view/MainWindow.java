package com.alexblakeappleby.cepsim.view;

import com.alexblakeappleby.cepsim.model.env.Environment;
import com.alexblakeappleby.cepsim.model.env.Tile;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow {
    public static void launch(){
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane(new EnvView(new Environment(10))));

        stage.setScene(scene);
        stage.show();
    }
}
