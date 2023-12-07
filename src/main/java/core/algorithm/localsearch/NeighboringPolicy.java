package core.algorithm.localsearch;

import core.base.OptimizationProblem;
import core.base.Solution;

public interface NeighboringPolicy {
    Solution getNeighbor(OptimizationProblem problem, Solution solution);

}
