package core.algorithm.nsga;


import core.base.ProblemModel;
import core.base.Representation;

public class DummyModel implements ProblemModel {
    @Override
    public boolean isFeasible(Representation rep) {
        return true;
    }
}
