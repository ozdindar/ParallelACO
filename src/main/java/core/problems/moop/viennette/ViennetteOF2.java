package core.problems.moop.viennette;


import core.ObjectiveFunction;
import core.ObjectiveType;
import core.base.ProblemModel;
import core.base.Representation;

public class ViennetteOF2 implements ObjectiveFunction {

    @Override
    public double value(ProblemModel model, Representation rep) {
        double x = ((DoubleVector)rep).get(0);
        double y = ((DoubleVector)rep).get(1);
        double val = (3*x -2*y + 4)*(3*x -2*y + 4)/8;
        val += (x-y+1)*(x-y+1)/27;
        val += 15;
        return val;
    }

    @Override
    public ObjectiveType type() {
        return ObjectiveType.Minimization;
    }


}
