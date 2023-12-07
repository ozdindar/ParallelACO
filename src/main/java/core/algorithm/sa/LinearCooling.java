package core.algorithm.sa;

import core.base.OptimizationProblem;

public class LinearCooling extends ClassicalCoolingSchedule {

    double delta;

    public LinearCooling(double tMax, double tMin,double delta) {
        super(tMax,tMin);
        this.delta= delta;
    }


    @Override
    public void updateTemp(OptimizationProblem problem) {
        currentTemperature -= delta;
    }
}
