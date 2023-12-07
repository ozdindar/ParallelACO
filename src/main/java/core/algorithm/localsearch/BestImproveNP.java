package core.algorithm.localsearch;

import core.base.OptimizationProblem;
import core.base.Solution;

import java.util.List;

/**
 * Author TBA
 */
public class BestImproveNP implements NeighboringPolicy{

    NeighboringIterator neighboringFunction;

    public BestImproveNP(NeighboringIterator neighboringFunction) {
        this.neighboringFunction = neighboringFunction;
    }

    @Override
    public Solution getNeighbor(OptimizationProblem problem, Solution solution) {
        /* todo: generate all neighbors according to neighboring function
        *  return the best*/

        Solution bestSolution = solution;
        List<Solution> neighbors = neighboringFunction.allNeighbors(problem,solution);
        for (Solution s :
                neighbors) {
            if (problem.objectiveType().betterThan(s.objectiveValue(), bestSolution.objectiveValue())) {
                bestSolution = s;
            }
        }
        return bestSolution.clone();
    }
}
