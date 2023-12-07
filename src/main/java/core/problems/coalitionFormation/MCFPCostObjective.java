package core.problems.coalitionFormation;

import core.ObjectiveFunction;
import core.ObjectiveType;
import core.base.ProblemModel;
import core.base.Representation;

public class MCFPCostObjective implements ObjectiveFunction {
    @Override
    public double value(ProblemModel model, Representation r) {
        MCFPModel mcfp = (MCFPModel) model;
        return mcfp.cost(r);
    }

    @Override
    public ObjectiveType type() {
        return ObjectiveType.Minimization;
    }
}
