package core.algorithm.sa;

import core.base.OptimizationProblem;
import core.base.Solution;

public interface NeighboringFunction {
    Solution generateNeighbor(OptimizationProblem problem, Solution solution);
}
