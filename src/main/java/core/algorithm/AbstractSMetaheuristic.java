package core.algorithm;

import core.algorithm.localsearch.SolutionGenerator;
import core.base.OptimizationProblem;
import core.base.Solution;

public abstract class AbstractSMetaheuristic extends AbstractMetaheuristic  {

    protected Solution currentSolution;

    public AbstractSMetaheuristic(AbstractSMetaheuristic other)
    {
        super(other.solutionGenerator);
    }

    public AbstractSMetaheuristic(SolutionGenerator solutionGenerator) {
        super(solutionGenerator);
    }

    protected Solution getCurrentSolution() {
        return currentSolution;
    }


    protected void init(OptimizationProblem problem) {
        super.init(problem);
        bestSolution= null;
        if (currentSolution == null)
            setCurrentSolution(problem,solutionGenerator.generate(problem));

        _init(problem);
    }

    protected abstract void _init(OptimizationProblem problem);


    public void setCurrentSolution(OptimizationProblem problem, Solution solution)
    {
        currentSolution =  solution;
        updateBest(problem,solution.clone());
    }



}
