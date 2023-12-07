package core.algorithm.ga;

import core.algorithm.SimpleSolution;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.representation.BitString;

import java.security.SecureRandom;
import java.util.Random;

public class FlipMutation implements MutationOperator {
    @Override
    public Solution apply(OptimizationProblem problem, Solution solution) {
        BitString bs = (BitString) solution.getRepresentation();
        Random r = new SecureRandom();

        bs.flip(r.nextInt(bs.length()));
        return new SimpleSolution(bs,problem.objectiveValues(bs));
    }
}
