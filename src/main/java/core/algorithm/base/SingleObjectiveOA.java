package core.algorithm.base;

import core.base.OptimizationProblem;
import core.base.Solution;

public interface SingleObjectiveOA extends OptimizationAlgorithm{

     Solution perform(OptimizationProblem problem);
}
