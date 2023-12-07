package core.algorithm.ga;

import core.base.OptimizationProblem;
import core.base.Solution;

import java.util.List;

/**
 * TODO
 */
public interface CrossOverOperator {
    List<Solution> apply(OptimizationProblem problem, Solution p1, Solution p2);
}
