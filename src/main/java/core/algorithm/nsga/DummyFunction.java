package core.algorithm.nsga;


import core.ObjectiveFunction;
import core.ObjectiveType;
import core.base.ProblemModel;
import core.base.Representation;

public class DummyFunction implements ObjectiveFunction {

    @Override
    public double value(ProblemModel model, Representation rep) {
        return 0;
    }

    @Override
    public ObjectiveType type() {
        return ObjectiveType.Minimization;
    }


}
