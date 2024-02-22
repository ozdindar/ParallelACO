package core.problems.moop.ctp1;

import core.algorithm.ga.MutationOperator;
import core.algorithm.nsga.RankedSolution;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.problems.moop.viennette.DoubleVector;

import java.security.SecureRandom;
import java.util.Random;

public class CTP1Mutation implements MutationOperator {

    Random rng = new SecureRandom();
    @Override
    public Solution apply(OptimizationProblem problem, Solution s) {
        DoubleVector dv1 = (DoubleVector) s.getRepresentation();

        double d1 = rng.nextBoolean()? rng.nextDouble()*3:-3*rng.nextDouble();
        double d2 = rng.nextBoolean()? rng.nextDouble()*3:-3*rng.nextDouble();
        double nodes[] = new double[]{ (dv1.get(0)+ d1)/2,(dv1.get(1)+d2)/2 };
        DoubleVector dv = new DoubleVector(nodes);

        return(new RankedSolution(dv,problem.objectiveValues(dv)));
    }
}
