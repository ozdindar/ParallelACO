package core.problems.moop.viennette;


import core.algorithm.localsearch.SolutionGenerator;
import core.algorithm.nsga.RankedSolution;
import core.base.OptimizationProblem;
import core.base.Solution;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ViennetteRandomSG implements SolutionGenerator<Solution> {
    Random rng= new SecureRandom();
    @Override
    public Solution generate(OptimizationProblem problem) {
        double[] nodes= new double[2];
        nodes[0] = rng.nextBoolean() ?  rng.nextDouble()*-3:rng.nextDouble()*3;
        nodes[1] = rng.nextBoolean() ?  rng.nextDouble()*-3:rng.nextDouble()*3;

        DoubleVector dv = new DoubleVector(nodes);
        return new RankedSolution(dv,problem.objectiveValues(dv));
    }

    @Override
    public List<Solution> generate(OptimizationProblem problem, int count) {
        List<Solution> solutions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            solutions.add((RankedSolution) generate(problem));
        }
        return solutions;
    }
}
