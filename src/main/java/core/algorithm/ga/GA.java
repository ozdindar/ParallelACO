package core.algorithm.ga;

import core.algorithm.AbstractMetaheuristic;
import core.algorithm.localsearch.SolutionGenerator;
import core.algorithm.localsearch.TerminalCondition;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.representation.base.Population;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA extends AbstractMetaheuristic {

    final TerminalCondition terminalCondition;

    final SelectionPolicy parentSelectionPolicy;
    final SelectionPolicy victimSelectionPolicy;

    final CrossOverOperator crossOverOperator;
    final MutationOperator mutationOperator;

    Population population= new ListPopulation();

    double crossOverRate=0.8;
    double mutationRate=0.1;

    Random r = new SecureRandom();
    private int parentCount= 10;
    private int populationSize=50;

    public GA(SolutionGenerator solutionGenerator, TerminalCondition terminalCondition, SelectionPolicy parentSelectionPolicy, SelectionPolicy victimSelectionPolicy, CrossOverOperator crossOverOperator, MutationOperator mutationOperator) {
        super(solutionGenerator);
        this.terminalCondition = terminalCondition;
        this.parentSelectionPolicy = parentSelectionPolicy;
        this.victimSelectionPolicy = victimSelectionPolicy;
        this.crossOverOperator = crossOverOperator;
        this.mutationOperator = mutationOperator;
    }

    @Override
    protected void _perform(OptimizationProblem problem) {

        while (!terminalCondition.isSatisfied(problem,this) )
        {
            List<Solution> parents = parentSelectionPolicy.select(problem,population.getIndividuals(),parentCount);
            List<Solution> offspring = applyCrossOver(problem,parents);
            offspring = applyMutation(problem,offspring);

            population.add(offspring);

            int victimCount = Math.max(0,population.size()-populationSize);
            List<Solution> victims = victimSelectionPolicy.select(problem,population.getIndividuals(),victimCount);
            population.removeAll(victims);
            //population = population.nextGeneration();
        }
    }

    private List<Solution> applyMutation(OptimizationProblem problem, List<Solution> offspring) {
        List<Solution> solutions = new ArrayList<>();
        for(Solution s:offspring)
        {
            if (r.nextDouble()<mutationRate) {
                s = mutationOperator.apply(problem, s);
                updateBest(problem,s);
            }
            solutions.add(s);
        }
        return solutions;
    }

    private List<Solution> applyCrossOver(OptimizationProblem problem, List<Solution> parents) {
        List<Solution> offspring = new ArrayList<>();
        for (int i = 0; i < parents.size()-1; i++) {
            for (int j = i+1; j <parents.size() ; j++) {
                if (r.nextDouble()<crossOverRate) {
                    List<Solution> list = crossOverOperator.apply(problem, parents.get(i), parents.get(j));
                    offspring.addAll(list);
                    updateBest(problem,list);
                }
            }
        }

        return offspring;
    }





    @Override
    protected void init(OptimizationProblem problem) {
        super.init(problem);
        bestSolution = null;
        population.clear();
        population.add(solutionGenerator.generate(problem,populationSize));
        updateBest(problem,population.getIndividuals());
        terminalCondition.init();
    }

    @Override
    public String getName() {
        return "GA";
    }
}
