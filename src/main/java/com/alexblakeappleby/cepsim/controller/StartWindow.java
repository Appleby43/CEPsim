package com.alexblakeappleby.cepsim.controller;

import com.alexblakeappleby.cepsim.model.exp.Experiment;
import com.alexblakeappleby.cepsim.view.ExperimentWindow;
import com.alexblakeappleby.cepsim.view.SimWindow;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartWindow {
    public static void launch(){
        Stage stage = new Stage();

        VBox vBox = new VBox(20);

        Button simButton = new Button("Start Simulation");
        simButton.setOnAction(e -> SimWindow.launch());

        Button expButton = new Button("Start Experiment");
        expButton.setOnAction(e -> new ExperimentWindow());

        vBox.getChildren().addAll(simButton, expButton);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }
}
