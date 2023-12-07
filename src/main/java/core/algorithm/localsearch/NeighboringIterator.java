package core.algorithm.localsearch;

import core.base.OptimizationProblem;
import core.base.Solution;

import java.util.List;

public interface NeighboringIterator {

    void reset();
    Solution nextNeighbor(OptimizationProblem problem, Solution solution);
    List<Solution> allNeighbors(OptimizationProblem problem, Solution solution);
}
