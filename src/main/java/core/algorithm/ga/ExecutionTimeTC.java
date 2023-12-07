package core.algorithm.ga;

import core.algorithm.base.OptimizationAlgorithm;
import core.algorithm.localsearch.TerminalCondition;
import core.base.OptimizationProblem;

public class ExecutionTimeTC implements TerminalCondition {

    public ExecutionTimeTC(ExecutionTimeTC executionTimeTC) {

    }

    @Override
    public boolean isSatisfied(OptimizationProblem problem, OptimizationAlgorithm alg) {
        return false;
    }

    @Override
    public void init() {

    }

    @Override
    public TerminalCondition clone() {
        return new ExecutionTimeTC(this);
    }
}
