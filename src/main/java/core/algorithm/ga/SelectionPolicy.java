package core.algorithm.ga;

import core.base.OptimizationProblem;
import core.base.Solution;

import java.util.List;

public interface SelectionPolicy {
    List<Solution> select(OptimizationProblem problem, List<Solution> population,int count);
}
