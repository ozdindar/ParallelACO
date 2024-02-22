package core.problems.moop.ctp1;

import core.ObjectiveFunction;
import core.ObjectiveType;
import core.base.ProblemModel;
import core.base.Representation;
import core.problems.moop.viennette.DoubleVector;

public class CTP1OF1 implements ObjectiveFunction {

    @Override
    public double value(ProblemModel model, Representation rep) {
        double x = ((DoubleVector)rep).get(0);
        return x;
    }

    @Override
    public ObjectiveType type() {
        return ObjectiveType.Minimization;
    }


}
