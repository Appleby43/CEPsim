package com.alexblakeappleby.cepsim.view;

import com.alexblakeappleby.cepsim.model.env.Environment;
import com.alexblakeappleby.cepsim.model.species.Species;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A window to display a single cepsim
 */
public class SimWindow {
    public static void launch(){
        Stage stage = new Stage();
        Environment environment = new Environment(50, Environment.Mode.TOROIDAL, new Species(.6), new Species(.4));
        EnvView envView = new EnvView(environment);

        PopGraph popGraph = new PopGraph(environment);
        environment.addProgressable(popGraph);

        environment.populateRandomly();
        //environment.populateCorners();
        popGraph.progress();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(popGraph.getGraphics(), envView);

        Scene scene = new Scene(vBox);

        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE){
                environment.progress();
            }
        });

        stage.setScene(scene);
        stage.setTitle("CEPsim");
        stage.show();

        Task<Double> task = new Task<>() {
            @Override
            protected Double call() throws Exception {
                for (int i = 0; i < 500; i++) {
                    if(isCancelled()){
                        break;
                    }

                    environment.progress();
                    popGraph.progress();
                    Thread.sleep(250);
                }
                return 0.0;
            }
        };

        Thread t = new Thread(task);
        t.setDaemon(true);
        //t.start();
    }
}
