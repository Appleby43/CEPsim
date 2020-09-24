package com.alexblakeappleby.cepsim.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class MainWindow {
    public static void launch(){
        Stage stage = new Stage();
        Scene scene = new Scene(new Region());

        stage.setScene(scene);
        stage.show();
    }
}
