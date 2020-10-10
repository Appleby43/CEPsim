package com.alexblakeappleby.cepsim.model.exp;

import com.alexblakeappleby.cepsim.model.env.Environment;
import com.alexblakeappleby.cepsim.model.species.Species;
import com.alexblakeappleby.cepsim.model.env.ProgressEvent;
import com.alexblakeappleby.cepsim.view.Event;

import java.util.ArrayList;
import java.util.List;

public class Trial {
    public static final int ENV_SIZE = 50;
    public final double compDifference;
    private final double s1, s2;
    private final int size, time;

    private Double result;

    private Event onFinishedEvent;
    /**
     * Keep an 'exampleEnvironment' (the first environment of the trial)
     * This environment can be observed by the viewer and graphed.
     */
    private Environment exampleEnvironment;

    public Trial(double s1, double s2, int size, int time) {
        this.s1 = s1;
        this.s2 = s2;
        this.size = size;
        this.time = time;

        exampleEnvironment = new Environment(ENV_SIZE, new Species(s1), new Species(s2));

        compDifference = Math.abs(s1 - s2);
    }

    public void run(){
        List<Boolean> results = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            boolean firstTrial = (i == 0);
            results.add(doRandomRun(firstTrial));
        }

        int trues = 0;
        for (Boolean result : results) {
            if (result) trues++;
        }

        result = (double)trues / (double)results.size();

        if(onFinishedEvent != null) onFinishedEvent.run();

    }

    public Environment getExampleEnvironment(){
        return exampleEnvironment;
    }

    public double getResult() {
        if(result == null){
            throw new Error("Asked for the result before calculation");
        }

        return result;
    }

    /**
     * @return true if the simulation shows coexistence, false otherwise.
     */
    private boolean doRandomRun(boolean firstTrial){
        Environment environment;

        if(firstTrial){
            environment = exampleEnvironment;
        } else {
            Species species1 = new Species(s1);
            Species species2 = new Species(s2);

            environment = new Environment(50, species1, species2);
        }

        environment.populateRandomly();

        for (int i = 0; i < time; i++) {
            environment.progress();
        }

        return environment.isCoexisting();
    }

    public void setOnFinishedEvent(Event onFinishedEvent){
        this.onFinishedEvent = onFinishedEvent;
    }
}
