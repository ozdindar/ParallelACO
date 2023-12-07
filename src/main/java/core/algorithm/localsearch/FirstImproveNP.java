package core.algorithm.localsearch;

import core.base.OptimizationProblem;
import core.base.Solution;

/**
 * Author : TBA
 */

public class FirstImproveNP implements NeighboringPolicy{

    NeighboringIterator neighboringFunction;

    public FirstImproveNP(NeighboringIterator neighboringFunction) {
        this.neighboringFunction = neighboringFunction;
    }

    @Override
    public Solution getNeighbor(OptimizationProblem problem, Solution solution) {
        /* todo: generate neighbors iteratively return the first improving*/
        Solution neighbor = neighboringFunction.nextNeighbor(problem,solution);
        while (neighbor!=null){
            if(problem.objectiveType().betterThan(neighbor.objectiveValue(),solution.objectiveValue())){
                return neighbor;
            }
            else{
                neighbor = neighboringFunction.nextNeighbor(problem,solution);
            }
        }

        return solution;
    }
}