package core.algorithm.sa;

import core.ObjectiveType;
import core.algorithm.AbstractSMetaheuristic;
import core.algorithm.localsearch.SolutionGenerator;
import core.algorithm.localsearch.TerminalCondition;
import core.base.OptimizationProblem;
import core.base.Solution;

import java.security.SecureRandom;
import java.util.Random;

public class SA  extends AbstractSMetaheuristic {

    private final CoolingSchedule coolingSchedule;
    private final TerminalCondition terminalCondition;
    private final TerminalCondition equilibrium;
    private final NeighboringFunction neighboring;
    private final AcceptanceFunction acceptanceFunction;
    private final Random rng = new SecureRandom();

    public SA(SolutionGenerator generator, CoolingSchedule coolingSchedule, TerminalCondition terminalCondition, TerminalCondition equilibrium, NeighboringFunction neighboring, AcceptanceFunction acceptanceFunction) {
        super(generator);
        this.coolingSchedule = coolingSchedule;
        this.terminalCondition = terminalCondition;
        this.equilibrium = equilibrium;
        this.neighboring = neighboring;
        this.acceptanceFunction = acceptanceFunction;
    }




    @Override
    protected void _perform(OptimizationProblem problem) {
        while (!coolingSchedule.cooledDown() && !terminalCondition.isSatisfied(problem,this))
        {
            optimizeAtThisTemp(problem);
            coolingSchedule.updateTemp(problem);
        }
    }

    private void optimizeAtThisTemp(OptimizationProblem problem) {
        equilibrium.init();
        while (!equilibrium.isSatisfied(problem,this))
        {
            Solution neighbor = neighboring.generateNeighbor(problem,getCurrentSolution());
            double deltaF = neighbor.objectiveValue()- getCurrentSolution().objectiveValue();

            if ( problem.objectiveType().betterThan(neighbor.objectiveValue(),getCurrentSolution().objectiveValue()))
                setCurrentSolution(problem,neighbor);
            else{
                deltaF =  problem.objectiveType()== ObjectiveType.Maximization ? -deltaF:deltaF;
                double acceptanceRatio =acceptanceFunction.ratio(deltaF,coolingSchedule.temperature());
                if ( rng.nextDouble()<acceptanceRatio)
                    setCurrentSolution(problem,neighbor);
            }
        }
    }

    @Override
    protected void _init(OptimizationProblem problem) {
        coolingSchedule.init(problem);
        terminalCondition.init();
    }

    @Override
    public String getName() {
        return "SA";
    }
}
