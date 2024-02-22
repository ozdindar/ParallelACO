package core.problems.moop.viennette;


import core.algorithm.ga.CrossOverOperator;
import core.algorithm.nsga.RankedSolution;
import core.base.OptimizationProblem;
import core.base.Solution;

import java.util.ArrayList;
import java.util.List;

public class VienetteCO implements CrossOverOperator {
    @Override
    public List<Solution> apply(OptimizationProblem problem, Solution s1, Solution s2) {
        DoubleVector dv1 = (DoubleVector) s1.getRepresentation();
        DoubleVector dv2 = (DoubleVector) s2.getRepresentation()
                ;

        double nodes[] = new double[]{ (dv1.get(0)+dv2.get(0))/2,(dv1.get(1)+dv2.get(1))/2 };
        DoubleVector dv = new DoubleVector(nodes);
        List<Solution> solutions = new ArrayList<>();
        solutions.add(new RankedSolution(dv,problem.objectiveValues(dv)));
        return solutions;
    }
}
