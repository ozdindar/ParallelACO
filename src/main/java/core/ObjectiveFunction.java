package core;

import core.base.ProblemModel;
import core.base.Representation;

public interface ObjectiveFunction {
    double value(ProblemModel model, Representation r);
    ObjectiveType type();
}