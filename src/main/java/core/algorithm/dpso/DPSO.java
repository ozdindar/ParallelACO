package core.algorithm.dpso;



import core.algorithm.AbstractMetaheuristic;
import core.algorithm.SimpleSolution;
import core.algorithm.dpso.base.DPSOParticle;
import core.algorithm.ga.CrossOverOperator;
import core.algorithm.ga.FlipMutation;
import core.algorithm.ga.MutationOperator;
import core.algorithm.ga.OnePointCrossover;
import core.algorithm.localsearch.SolutionGenerator;
import core.algorithm.localsearch.TerminalCondition;
import core.base.OptimizationProblem;
import core.base.Representation;
import core.base.Solution;
import core.utils.random.RandUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dindar.oz on 03.06.2015.
 */

/**
 * this Discrete PSO is written according to
 * See: Pan, tasgetiren, Liang, A Discrete PSO algorithm for the no-wait
 * flowshop scheduling problem, 2008, (journal)
 * @author falkaya
 *
 */
public class DPSO extends AbstractMetaheuristic {

    private static final int MUTATION_COUNT = 1;
    double w;
    double siP;
    double siG;

    int iterationCount =0;

    List<DPSOParticle> swarm;
    int swarmSize;

    //double minCost = 0;

    int reHopeCount =0;



    private final TerminalCondition terminalCondition;
    private final MutationOperator mutation = new FlipMutation();
    private final CrossOverOperator crossOver = new OnePointCrossover();

    public DPSO(double w, double siP, double siG, int swarmSize, TerminalCondition terminalCondition, SolutionGenerator solutionGenerator) {
        super(solutionGenerator);
        this.w = w;
        this.siP = siP;
        this.siG = siG;
        this.swarmSize = swarmSize;
        this.terminalCondition= terminalCondition;
    }


    @Override
    protected void init(OptimizationProblem problem) {
        super.init(problem);
        terminalCondition.init();
        reHopeCount=0;
        bestSolution = null;
    }

    @Override
    protected void _perform(OptimizationProblem problem)
    {
        reHopeCount =0;
        List<Solution> initialPositions = solutionGenerator.generate(problem,swarmSize);
        swarm = generateInitialParticles(problem, initialPositions);

        while (!terminalCondition.isSatisfied(problem,this)){
            for (DPSOParticle particle : swarm) {
                updateParticle(problem, particle, w, siG, siP);
            }

            if (noHopeCase())
            {
                reHopeCount++;
                reHope(problem,solutionGenerator);
            }

            iterationCount++;
        }
    }

    @Override
    public String getName() {
        return "[DPSO: "+w+"-"+siP+"-"+siG+"-"+swarmSize+"]";
    }

    private void reHope(OptimizationProblem problem,SolutionGenerator solutionGenerator) {
        List<Solution> newPositions = solutionGenerator.generate(problem,swarmSize);
        swarm= generateInitialParticles(problem,newPositions);
    }

    private boolean noHopeCase() {

        boolean reHopeEnabled = true;
        if (!reHopeEnabled)
            return false;

        int diversity = 0;
        for (DPSOParticle p : swarm) {
            if (!p.getPosition().equals(bestSolution.getRepresentation())) {
                diversity++;
                continue;
            }
            if (!p.getPosition().equals(p.getBestKnownPosition())) {
                diversity++;
            }

        }

        return diversity<swarm.size()/2;
    }


    private List<DPSOParticle> generateInitialParticles(OptimizationProblem problem, List<Solution> initialPositions)
    {
        List<DPSOParticle> pList = new ArrayList<>();
        for (Solution initialPosition: initialPositions)
        {
            double cost = initialPosition.objectiveValue();
            Representation rep = initialPosition.getRepresentation();
            DPSOParticle particle = new SimpleDPSOParticle(cost,rep,rep.clone(),cost);

            updateBest( problem,new SimpleSolution( particle.getPosition(),particle.getCost()));

            pList.add(particle);

        }
        return pList;
    }

    public void updateParticle(OptimizationProblem psoProblem, DPSOParticle particle,double w,double siG,double siP) {

        Representation r = particle.getPosition().clone();
        double cost = particle.getCost();
        Solution s = new SimpleSolution(r,cost);

        if (RandUtils.randBoolean(w)) // Mutate by itself
        {
            s = mutation.apply(psoProblem,s);
        }

        if (RandUtils.randBoolean(siP)) //  CrossOver by personal best
        {
            Solution bestS = new SimpleSolution(particle.getBestKnownPosition(),particle.getBestKnownCost());
            s = crossOver.apply(psoProblem,s, bestS).get(0);
        }

        if (RandUtils.randBoolean(siG)) //  CrossOver by global best
        {
            s = crossOver.apply(psoProblem,s,bestSolution).get(0);
        }

        particle.update(s.getRepresentation(), s.objectiveValue());
        updateBest(psoProblem,s);
    }



}
