package core.problems.wsn;


import core.base.OptimizationProblem;
import core.base.Representation;
import core.base.Solution;
import core.representation.BitString;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

public class BitStringSolution implements Solution {

    Representation representation;
    double objectiveValue;

    public BitStringSolution(Representation representation, double objectiveValue) {
        this.representation = representation;
        this.objectiveValue = objectiveValue;
    }

    public void setObjectiveValue(double objectiveValue) {
        this.objectiveValue = objectiveValue;
    }

    @Override
    public Solution clone() {
        return new BitStringSolution(representation.clone(), objectiveValue);
    }

    @Override
    public Representation getRepresentation() {
        return representation;
    }

    @Override
    public double[] objectiveValues() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public double objectiveValue(int index) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public double objectiveValue() {
        return objectiveValue;
    }

    public static Solution generate(OptimizationProblem problem, int solutionSize) {
        Random random = new SecureRandom();
        BitSet bitSet = new BitSet(solutionSize);
        for (int i = 0; i < solutionSize; i++) {
            bitSet.set(i, random.nextBoolean());
        }
        BitString bitString = new BitString(bitSet,solutionSize);
        return new BitStringSolution(bitString, problem.objectiveValue(bitString));
    }

    public static List<Solution> generate(OptimizationProblem problem, int solutionSize, int count) {
        List<Solution> solutions = new ArrayList<>();
        for (int c = 0; c < count; c++) {
            solutions.add(generate(problem, solutionSize));
        }
        return solutions;
    }

    public String toString() {
        return representation.toString() + " " + objectiveValue;
    }
}
