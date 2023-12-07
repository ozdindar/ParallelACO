package core.algorithm.localsearch;

import core.algorithm.base.OptimizationAlgorithm;
import core.base.OptimizationProblem;

/**
 * Author: TBA
 */
public class IterationBasedTC implements TerminalCondition {

    private int iterationLimit;
    private int currentIteration;

    public IterationBasedTC(int iterationLimit) {
        this.iterationLimit = iterationLimit;
        init();
    }

    @Override
    public boolean isSatisfied(OptimizationProblem problem, OptimizationAlgorithm alg) {
        if(currentIteration<iterationLimit){
            currentIteration++;
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void init() {
        currentIteration = 0;
    }

    @Override
    public TerminalCondition clone() {
        return new IterationBasedTC(iterationLimit);
    }
}
