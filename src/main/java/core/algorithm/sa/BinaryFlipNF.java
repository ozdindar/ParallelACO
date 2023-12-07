package core.algorithm.sa;

import core.algorithm.SimpleSolution;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.representation.BitString;

import java.security.SecureRandom;
import java.util.Random;

public class BinaryFlipNF implements NeighboringFunction{
    Random r = new SecureRandom();
    @Override
    public Solution generateNeighbor(OptimizationProblem problem, Solution solution) {
        BitString bs = (BitString) solution.getRepresentation();
        bs.flip(r.nextInt(bs.length()));
        return new SimpleSolution(bs,problem.objectiveValues(bs));
    }
}
