package core.problems.moop.ctp1;


import core.ObjectiveFunction;
import core.ObjectiveType;
import core.base.ProblemModel;
import core.base.Representation;
import core.problems.moop.viennette.DoubleVector;

public class CTP1OF2 implements ObjectiveFunction {

    @Override
    public double value(ProblemModel model, Representation rep) {
        double x = ((DoubleVector)rep).get(0);
        double y = ((DoubleVector)rep).get(1);
        double val = (1+y)*Math.exp(-x/1+y);
        return val;
    }

    @Override
    public ObjectiveType type() {
        return ObjectiveType.Minimization;
    }

}
