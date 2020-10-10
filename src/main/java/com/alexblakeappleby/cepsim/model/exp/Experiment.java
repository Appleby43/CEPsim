package com.alexblakeappleby.cepsim.model.exp;

/**
 * An Experiment contains multiple Trials, distributed evenly over a range of competitive differences.
 * @see Trial
 */
public class Experiment {
    public final int trials;
    public final int trialSize;
    public final int trialTime;
    private TrialEvent beforeTrialEvent, afterTrialEvent;

    public Experiment(int trials, int trialSize, int trialTime) {
        this.trials = trials;
        this.trialSize = trialSize;
        this.trialTime = trialTime;
    }

    public void start(){
        //how much will the competitive difference change per trial?
        //since the first trial has no increment, we must reach 0 competitive difference in t - 1 trials
        double incrementSize = 0.5 / (trials - 1);

        for (int i = 0; i < trials; i++){
            //note that s1 + s2 = 1
            double s1 = incrementSize * i;
            double s2 = 1 - s1;

            Trial t = new Trial(s1, s2, trialSize, trialTime);
            if(beforeTrialEvent != null) beforeTrialEvent.next(t, i);
            t.run();
            if(afterTrialEvent != null) afterTrialEvent.next(t, i);
        }
    }

    public void setAfterTrialEvent(TrialEvent afterTrialEvent){
        this.afterTrialEvent = afterTrialEvent;
    }

    public void setBeforeTrialEvent(TrialEvent beforeTrialEvent) {
        this.beforeTrialEvent = beforeTrialEvent;
    }

    public interface TrialEvent {
        void next(Trial trial, int index);
    }
}
