package core.algorithm.localsearch;

import core.algorithm.base.OptimizationAlgorithm;
import core.base.OptimizationProblem;

public interface TerminalCondition {
    boolean isSatisfied(OptimizationProblem problem, OptimizationAlgorithm alg);

    void init();

    TerminalCondition clone();
}
