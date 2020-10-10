package com.alexblakeappleby.cepsim.view;

import com.alexblakeappleby.cepsim.model.env.Environment;
import com.alexblakeappleby.cepsim.model.exp.Experiment;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ExperimentWindow {
    private Experiment experiment;
    private List<PopGraph> popGraphs = new ArrayList<>();

    public ExperimentWindow() {
        Stage stage = new Stage();
        experiment = new Experiment(100, 100, 100);

        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);

        VBox vBox = new VBox();
        vBox.setFillWidth(true);

        scrollPane.setContent(vBox);

        experiment.setBeforeTrialEvent((t, i) -> {
            Environment exampleEnvironment = t.getExampleEnvironment();
            PopGraph popGraph = new PopGraph(t.getExampleEnvironment());
            exampleEnvironment.addProgressable(popGraph);

            popGraphs.add(i, popGraph);
        });

        experiment.setAfterTrialEvent((t, i) -> {
            Task<Void> updateUiTask = new Task<>() {
                @Override
                protected Void call() {

                    //create all these UI elements for every trial.
                    HBox hBox = new HBox(20);


                    if(i % 2 == 0){
                        hBox.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
                    }

                    Text trialLabel = new Text("Trial: " + i);
                    Text difLabel = new Text("Competitive Difference: " + t.compDifference);
                    Text resultLabel = new Text("Result: " + t.getResult());

                    Button graphButton = new Button("Show Graph");
                    Stage graphStage = new Stage();
                    Scene graphScene = new Scene(popGraphs.get(i).getGraphics());
                    graphStage.setScene(graphScene);
                    graphButton.setOnAction(e -> {
                        graphStage.show();
                    });

                    hBox.getChildren().addAll(trialLabel, difLabel, resultLabel, graphButton);

                    vBox.getChildren().add(hBox);
                    return null;
                }
            };
            Platform.runLater(updateUiTask);
        });

        stage.setScene(new Scene(scrollPane));
        stage.show();

        //experiment.start();

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                experiment.start();
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        task.exceptionProperty().addListener((observable, oldValue, newValue) ->  {
            if(newValue != null) {
                Exception ex = (Exception) newValue;
                ex.printStackTrace();
            }
        });

        //Platform.runLater(task);

    }
}
