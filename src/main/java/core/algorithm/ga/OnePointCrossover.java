package core.algorithm.ga;

import core.algorithm.SimpleSolution;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.representation.BitString;

import java.util.ArrayList;
import java.util.List;

public class OnePointCrossover implements CrossOverOperator {
    @Override
    public List<Solution> apply(OptimizationProblem problem, Solution p1, Solution p2) {
        BitString bs1 = (BitString) p1.getRepresentation();
        BitString bs2 = (BitString) p2.getRepresentation();
        int mid = bs1.length()/2;
        BitString bs3 = new BitString(bs1.length());
        BitString bs4 = new BitString(bs1.length());

        for (int i = 0; i < mid; i++) {
            bs3.set(i,bs1.get(i));
            bs4.set(i,bs2.get(i));
        }
        for (int i = mid; i < bs1.length(); i++) {
            bs3.set(i,bs2.get(i));
            bs4.set(i,bs1.get(i));
        }
        List<Solution> solutions = new ArrayList<>();
        solutions.add(new SimpleSolution(bs3,problem.objectiveValues(bs3)));
        solutions.add(new SimpleSolution(bs4,problem.objectiveValues(bs4)));
        return solutions;
    }
}
