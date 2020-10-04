package com.alexblakeappleby.cepsim.view;

import com.alexblakeappleby.cepsim.model.env.Environment;
import com.alexblakeappleby.cepsim.model.species.Species;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A graph of the population of species on the grid.
 */
public class PopGraph extends LineChart<Number, Number> {
    private final Environment environment;

    Map<Species, XYChart.Series<Number, Number>> seriesMap = new HashMap<>();

    public PopGraph(Environment environment) {
        super(new NumberAxis(), new NumberAxis());
        this.environment = environment;
        Species[] envSpecies = environment.species;

        for (int i = 0; i < envSpecies.length; i++) {
            Species species = envSpecies[i];

            XYChart.Series<Number, Number> series = new XYChart.Series<>();

            seriesMap.put(species, series);

            getData().add(series);

            Node seriesNode = lookup(".series" + i);
            Color color = ColorRegistry.getSpeciesColor(i);

            seriesNode.setStyle("-fx-stroke: rgb(" + color.getRed() * 255 + ", " + color.getGreen() * 255 + ", " + color.getBlue() * 255 + ")");
        }

    }

    public void progress(){
        for (Species s : environment.species) {
            XYChart.Series<Number, Number> series = seriesMap.get(s);

            XYChart.Data<Number, Number> data = new XYChart.Data<>(environment.getTime(), s.populationCount());
            data.setNode(new Circle(0));

            series.getData().add(data);
        }
    }
}
