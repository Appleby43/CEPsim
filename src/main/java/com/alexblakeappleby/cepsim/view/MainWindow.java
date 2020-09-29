package com.alexblakeappleby.cepsim.view;

import com.alexblakeappleby.cepsim.model.env.Environment;
import com.alexblakeappleby.cepsim.model.species.Species;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow {
    public static void launch(){
        Stage stage = new Stage();
        Environment environment = new Environment(10);
        EnvView envView = new EnvView(environment);

        environment.populateRandomly(new Species(1), new Species(1));

        Scene scene = new Scene(new StackPane(envView));

        stage.setScene(scene);
        stage.show();
    }
}
