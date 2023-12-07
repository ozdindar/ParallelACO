package core.algorithm.localsearch;

import core.algorithm.base.OptimizationAlgorithm;
import core.base.OptimizationProblem;

public class TimeBasedTC implements TerminalCondition{
    long endTime;
    long timeLimit;

    public TimeBasedTC(long timeLimit) {
        this.timeLimit = timeLimit;
        init();
    }

    @Override
    public boolean isSatisfied(OptimizationProblem problem, OptimizationAlgorithm alg) {
        long currentTime = System.currentTimeMillis();
        if(currentTime<endTime){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public void init() {
        endTime = System.currentTimeMillis()+timeLimit;
    }

    @Override
    public TerminalCondition clone() {
        return new TimeBasedTC(timeLimit);
    }
}
