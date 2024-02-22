package core.algorithm.localsearch;

import core.base.OptimizationProblem;
import core.base.Solution;

import java.util.ArrayList;
import java.util.List;

public interface SolutionGenerator<S extends Solution> {
    S generate(OptimizationProblem problem);

    default List<S> generate(OptimizationProblem problem, int count)
    {
        List<S> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {

            list.add(generate(problem));
        }
        return list;
    }


}
