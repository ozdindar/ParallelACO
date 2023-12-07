package core.base;

import core.ObjectiveType;

public interface OptimizationProblem {
    ProblemModel model();


    double objectiveValue(Representation r);
    double objectiveValue(int index, Representation r);

    double[] objectiveValues(Representation r);
    ObjectiveType objectiveType(int index);
    ObjectiveType objectiveType();
}
