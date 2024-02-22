package core.problems.moop.viennette;


import core.ObjectiveFunction;
import core.ObjectiveType;
import core.base.ProblemModel;
import core.base.Representation;

public class ViennetteOF3 implements ObjectiveFunction {

    @Override
    public double value(ProblemModel model, Representation rep) {
        double x = ((DoubleVector)rep).get(0);
        double y = ((DoubleVector)rep).get(1);
        double val = 1/(x*x+ y*y +1);
        val -= 1.1* Math.exp(-(x*x+y*y));
        return val;
    }

    @Override
    public ObjectiveType type() {
        return ObjectiveType.Minimization;
    }


}
