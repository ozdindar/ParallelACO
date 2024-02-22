package core.algorithm.base;


import core.base.OptimizationProblem;
import core.base.Solution;

import java.util.List;

public interface MultiObjectiveOA extends OptimizationAlgorithm {
    List<Solution> perform(OptimizationProblem problem);
}
