package core;

import core.base.OptimizationProblem;
import core.base.ProblemModel;
import core.base.Representation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NullModel implements ProblemModel
{

    @Override
    public boolean isFeasible(Representation rep) {
        return true;
    }
}


public class SimpleOptimizationProblem implements OptimizationProblem {

    ProblemModel model= new NullModel();
    List<ObjectiveFunction> objectives;

    public SimpleOptimizationProblem(ProblemModel model) {
        this.model = model;
        objectives = new ArrayList<>();
    }

    public SimpleOptimizationProblem() {
        objectives = new ArrayList<>();
    }

    public void addObjective(ObjectiveFunction objective)
    {
        objectives.add(objective);
    }

    @Override
    public ProblemModel model() {
        return model;
    }

    @Override
    public double objectiveValue(Representation r) {
        assert !objectives.isEmpty();

        return objectives.get(0).value(model,r);
    }

    @Override
    public double objectiveValue(int index, Representation r) {
        assert objectives.size()>index;
        return objectives.get(index).value(model,r);
    }

    @Override
    public double[] objectiveValues(Representation r) {
        return objectives.stream().mapToDouble((x)-> x.value(model,r)).toArray();
    }

    @Override
    public ObjectiveType objectiveType(int index) {
        return objectives.get(index).type();
    }

    @Override
    public ObjectiveType objectiveType() {
        return objectives.get(0).type();
    }

    @Override
    public int objectiveCount() {
        return objectives.size();
    }

    @Override
    public void addObjective(ObjectiveFunction... objList) {
        objectives.addAll(Arrays.asList(objList));
    }
}
