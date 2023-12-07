package core.algorithm.ils;

import core.algorithm.AbstractSMetaheuristic;
import core.algorithm.SimpleSolution;
import core.algorithm.localsearch.SolutionGenerator;
import core.algorithm.localsearch.TerminalCondition;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.representation.Permutation;
import core.utils.random.RandUtils;

public class ILS extends AbstractSMetaheuristic {

    AbstractSMetaheuristic sMetaheuristic;
    double perturbateRate;
    int perturbationInterval;
    TerminalCondition terminalCondition;

    public ILS(SolutionGenerator solutionGenerator, AbstractSMetaheuristic sMetaheuristic, double perturbateRate, int perturbationInterval, TerminalCondition terminalCondition) {
        super(solutionGenerator);
        this.sMetaheuristic = sMetaheuristic;
        this.perturbateRate = perturbateRate;
        this.perturbationInterval = perturbationInterval;
        this.terminalCondition = terminalCondition;
    }

    @Override
    protected void _perform(OptimizationProblem problem) {
        while(!terminalCondition.isSatisfied(problem,this)) {
            perturbate(problem);
            for (int i = 0; i < perturbationInterval; i++) {
                sMetaheuristic.setCurrentSolution(problem,getCurrentSolution());
                setCurrentSolution(problem, sMetaheuristic.perform(problem));
            }
        }
    }

    private void perturbate(OptimizationProblem problem) {
        Solution solution = getCurrentSolution();
        Permutation permutation = (Permutation) solution.getRepresentation();
        int perturbationNumber = (int) (permutation.size()*perturbateRate);
        for (int i = 0; i < perturbationNumber / 2; i++) {
            int firstIndex = RandUtils.randInt(permutation.size());
            int secondIndex = RandUtils.randInt(permutation.size());
            permutation.swap(firstIndex,secondIndex);
        }
        setCurrentSolution(problem,new SimpleSolution(permutation,problem.objectiveValues(permutation)));
    }

    @Override
    protected void _init(OptimizationProblem problem) {
        sMetaheuristic.setCurrentSolution(problem,getCurrentSolution());
        setCurrentSolution(problem, sMetaheuristic.perform(problem));
    }

    @Override
    public String getName() {
        return "ILS";
    }
}
