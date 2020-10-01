package com.alexblakeappleby.cepsim.view;

import com.alexblakeappleby.cepsim.model.env.Environment;
import com.alexblakeappleby.cepsim.model.species.Species;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow {
    public static void launch(){
        Stage stage = new Stage();
        Environment environment = new Environment(50);
        EnvView envView = new EnvView(environment);

        environment.populateRandomly(new Species(.9), new Species(1));
        //environment.populateCorners();

        Scene scene = new Scene(new StackPane(envView));

//        scene.setOnMouseClicked(e -> {
//        });

        stage.setScene(scene);
        stage.show();

        Thread t = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    environment.progress();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void kill(){}
        };

        t.start();

    }
}
