package core.problems.moop.ctp1;


import core.algorithm.ga.CrossOverOperator;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.problems.moop.viennette.DoubleVector;

import java.util.ArrayList;
import java.util.List;

public class CTP1CO implements CrossOverOperator {
    @Override
    public List<Solution> apply(OptimizationProblem problem, Solution s1, Solution s2) {
        DoubleVector dv1 = (DoubleVector) s1.getRepresentation();
        DoubleVector dv2 = (DoubleVector) s2.getRepresentation();

        double nodes[] = new double[]{ (dv1.get(0)+dv2.get(0))/2,(dv1.get(1)+dv2.get(1))/2 };
        DoubleVector dv = new DoubleVector(nodes);
        List<Solution> solutions = new ArrayList<>();
        return solutions;
    }
}
