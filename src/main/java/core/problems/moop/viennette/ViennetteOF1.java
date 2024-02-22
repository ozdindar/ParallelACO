package core.problems.moop.viennette;


import core.ObjectiveFunction;
import core.ObjectiveType;
import core.base.ProblemModel;
import core.base.Representation;

import static java.lang.Math.sin;

public class ViennetteOF1  implements ObjectiveFunction {

    @Override
    public double value(ProblemModel model, Representation rep) {
        double x = ((DoubleVector)rep).get(0);
        double y = ((DoubleVector)rep).get(1);
        return 0.5*(x*x + y*y) + sin(x*x + y*y);
    }

    @Override
    public ObjectiveType type() {
        return ObjectiveType.Minimization;
    }


}
