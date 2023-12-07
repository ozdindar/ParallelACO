package core.algorithm.ga;

import core.base.OptimizationProblem;
import core.base.Solution;

/**
 * TODO
 */
public interface MutationOperator {
    public Solution apply(OptimizationProblem problem, Solution solution) ;
}
