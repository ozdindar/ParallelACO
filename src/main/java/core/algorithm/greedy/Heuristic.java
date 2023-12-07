package core.algorithm.greedy;

import core.base.ProblemModel;

public interface Heuristic< M extends ProblemModel,T> {

    double value(M m, T t);

}
