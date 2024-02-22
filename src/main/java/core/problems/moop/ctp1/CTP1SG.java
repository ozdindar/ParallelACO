package core.problems.moop.ctp1;


import core.algorithm.localsearch.SolutionGenerator;
import core.algorithm.nsga.RankedSolution;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.problems.moop.viennette.DoubleVector;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CTP1SG implements SolutionGenerator<Solution> {
    Random rng= new SecureRandom();
    @Override
    public RankedSolution generate(OptimizationProblem problem) {

        DoubleVector dv;
        do {
            double[] nodes = new double[2];
            nodes[0] = rng.nextDouble();
            nodes[1] = rng.nextDouble();
            dv = new DoubleVector(nodes);
        }
        while (!problem.model().isFeasible(dv));

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
