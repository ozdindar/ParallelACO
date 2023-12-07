package core.problems.wsn;

import core.algorithm.SimpleSolution;
import core.algorithm.localsearch.SolutionGenerator;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.representation.BitString;

public class RandomBitStringGenerator implements SolutionGenerator {
    private final int len;

    public RandomBitStringGenerator(int solutionSize) {
        len = solutionSize;
    }

    @Override
    public Solution generate(OptimizationProblem problem) {
        BitString bs = BitString.random(len);
        return new SimpleSolution(bs,problem.objectiveValues(bs));
    }


}
