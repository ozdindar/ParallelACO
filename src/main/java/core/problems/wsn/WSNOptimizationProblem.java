package core.problems.wsn;


import core.ObjectiveFunction;
import core.ObjectiveType;
import core.base.OptimizationProblem;
import core.base.ProblemModel;
import core.base.Representation;

public class WSNOptimizationProblem implements OptimizationProblem {
    ProblemModel model;
    ObjectiveFunction objectiveFunction;

    public WSNOptimizationProblem(ProblemModel model, ObjectiveFunction objectiveFunction) {
        this.model = model;
        this.objectiveFunction = objectiveFunction;
    }

    @Override
    public ProblemModel model() {
        return model;
    }

    @Override
    public double objectiveValue(Representation r) {
        return objectiveFunction.value(model, r);
    }

    @Override
    public double objectiveValue(int index, Representation r) {
        throw new RuntimeException("Not Implemented..");
    }

    @Override
    public double[] objectiveValues(Representation r) {
        return new double[] { objectiveValue(r)};
    }

    @Override
    public ObjectiveType objectiveType(int index) {
        throw new RuntimeException("Not Implemented..");
    }

    @Override
    public ObjectiveType objectiveType() {
        return objectiveFunction.type();
    }

    @Override
    public int objectiveCount() {
        return 1;
    }

    @Override
    public void addObjective(ObjectiveFunction... objList) {
        throw new RuntimeException("You can not add objective to a Singole-Objective OA");
    }
}
