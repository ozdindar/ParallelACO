package core.algorithm.sa;

import core.base.OptimizationProblem;

public interface CoolingSchedule {
    void init(OptimizationProblem problem);

    boolean cooledDown();

    void updateTemp(OptimizationProblem problem);

    double temperature();
}
