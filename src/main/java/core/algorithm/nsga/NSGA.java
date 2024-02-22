package core.algorithm.nsga;



import core.SimpleOptimizationProblem;
import core.algorithm.Iterating;
import core.algorithm.base.MultiObjectiveOA;
import core.algorithm.ga.CrossOverOperator;
import core.algorithm.ga.MutationOperator;
import core.algorithm.localsearch.SolutionGenerator;
import core.algorithm.localsearch.TerminalCondition;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.problems.moop.ctp1.*;
import core.problems.moop.viennette.*;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

public class NSGA implements MultiObjectiveOA, Iterating {

    Random rng = new SecureRandom();
    SolutionGenerator<Solution> solutionGenerator;
    List<NSGANode> nodes;
    TerminalCondition terminalCondition;
    NSGA2Ranking nsga2Ranking;
    private int populationSize;
    long iterationCount =0;
    List<Solution> solutions;


    double crossoverRate;
    double mutationRate;
    CrossOverOperator crossover;
    MutationOperator mutation;
    private final double generationRate=0.1;

    public NSGA(SolutionGenerator<Solution> solutionGenerator, TerminalCondition terminalCondition, CrossOverOperator crossover, MutationOperator mutation) {
        this.solutionGenerator = solutionGenerator;
        this.terminalCondition = terminalCondition;
        this.crossover = crossover;
        this.mutation = mutation;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setIterationCount(long iterationCount) {
        this.iterationCount = iterationCount;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public List<NSGANode> createNodes(List<Solution> population) {
        List<NSGANode> nsgaNodes = new ArrayList<>();
        for (Solution s:population) {
            nsgaNodes.add(new NSGANode((RankedSolution) s));
        }
        return nsgaNodes;
    }


    @Override
    public List<Solution> perform(OptimizationProblem problem) {
        init(problem);
        while(!terminalCondition.isSatisfied(problem,this))
        {
            List<Solution> offsprings = applyCrossovers(problem);
            applyMutations(problem,offsprings);

            nodes = createNodes(solutions);
            List<Set<NSGANode>> fronts = nsga2Ranking.rank(nodes,problem);
            nodes = nsga2Ranking.calculateCrowdingDistances(problem,fronts);

            trimPopulation(problem);
            iterationCount++;
        }

        return nondominateds();
    }

    private void trimPopulation(OptimizationProblem problem) {

        nodes.sort((n1,n2)-> {
            if (n1.solution.rank!= n2.solution.rank)
                return Integer.compare(n1.solution.rank,n2.solution.rank);
            else return Double.compare(-n1.crowdingDistance,-n2.crowdingDistance);
        });

        nodes = nodes.stream().limit(populationSize).collect(Collectors.toList());
        solutions.clear();
        solutions.addAll(nodes.stream().map(x->x.solution).toList());
    }

    private void applyMutations(OptimizationProblem problem, List<Solution> offsprings) {
        List<Solution> mutants = new ArrayList<>();
        for(Solution s1:offsprings)
        {
            if (rng.nextDouble()<mutationRate)
            {
                Solution s = mutation.apply(problem,s1);
                if (problem.model().isFeasible(s.getRepresentation()))
                    mutants.add(s);
            }
        }
        solutions.addAll(mutants);
    }

    private List<Solution> applyCrossovers(OptimizationProblem problem) {
        List<Solution> offSprings = new ArrayList<>();
        Collections.shuffle(solutions);
        for(Solution s1:solutions)
        {
            for(Solution s2:solutions)
            {
                if (s1!=  s2 && rng.nextDouble()<crossoverRate)
                {
                    List<Solution> newBorns = crossover.apply(problem,s1,s2);
                    for (Solution s:newBorns) {
                        if (problem.model().isFeasible(s.getRepresentation())) {
                            offSprings.add(s);
                            if (offSprings.size() >= populationSize * generationRate)
                            {
                                return offSprings;
                            }
                        }
                    }
                }
            }
        }
        return offSprings;
    }

    private List<Solution> nondominateds() {
        return nodes.stream().filter((x)->x.solution.rank==1).map((x)->x.solution).collect(Collectors.toList());
    }

    private void init(OptimizationProblem problem) {
        solutions = solutionGenerator.generate(problem,populationSize);
        nsga2Ranking = new NSGA2Ranking();
        iterationCount=0;

    }


    @Override
    public long iterationcount() {
        return iterationCount;
    }



    private static void solveCTP1() {
        OptimizationProblem ctp1 = new SimpleOptimizationProblem(new CTP1());
        ctp1.addObjective(new CTP1OF1(),new CTP1OF2());

        NSGA alg = new NSGA(new CTP1SG(),new IterationCountTC(10000),new CTP1CO(),new CTP1Mutation());
        alg.setCrossoverRate(0.2);
        alg.setMutationRate(0.1);
        alg.setPopulationSize(50);
        List<Solution> solutions = alg.perform(ctp1);

        solutions.forEach(System.out::println);
    }

    private static void solveVienette() {
        OptimizationProblem vienette = new SimpleOptimizationProblem();
        vienette.addObjective(new ViennetteOF1(),new ViennetteOF2(),new ViennetteOF3());

        NSGA alg = new NSGA(new ViennetteRandomSG(),new IterationCountTC(100),new VienetteCO(),new VienetteMutation());
        alg.setCrossoverRate(0.2);
        alg.setMutationRate(0.1);
        alg.setPopulationSize(50);
        List<Solution> solutions = alg.perform(vienette);

        solutions.forEach(System.out::println);
    }

    @Override
    public String getName() {
        return "NSGA";
    }

    public static void main(String[] args) {
        solveCTP1();
        //solveVienette();
    }
}
