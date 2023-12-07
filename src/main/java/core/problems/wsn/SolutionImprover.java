package core.problems.wsn;


import core.base.OptimizationProblem;
import core.base.Solution;

import java.util.List;

public interface SolutionImprover {

    Solution improve(OptimizationProblem problem, Solution solution);

    List<Solution> improveAll(OptimizationProblem problem, List<Solution> solutions);
}
