package core.algorithm;

import core.algorithm.base.SingleObjectiveOA;
import core.algorithm.localsearch.SolutionGenerator;
import core.base.OptimizationProblem;
import core.base.Solution;
import core.utils.FileUtils;

import java.util.List;

public abstract class AbstractMetaheuristic implements SingleObjectiveOA {

    protected Solution bestSolution;
    protected SolutionGenerator solutionGenerator;
    private long startTime;
    private long bestAchieveTime;

    private String convergeAnalysisFileName="";


    public void setConvergeAnalysisFileName(String fileName)
    {
        convergeAnalysisFileName=fileName;
    }

    public AbstractMetaheuristic(SolutionGenerator solutionGenerator) {
        this.solutionGenerator = solutionGenerator;
    }

    public Solution getBestSolution() {
        return bestSolution;
    }

    public long getBestAchieveTime() {
        return bestAchieveTime;
    }

    @Override
    public Solution perform(OptimizationProblem problem) {
        init(problem);
        _perform(problem);

        return bestSolution;
    }

    protected void init(OptimizationProblem problem)
    {
        startTime = System.currentTimeMillis();
        if (!convergeAnalysisFileName.isEmpty())
        {
            FileUtils.writeToFile(convergeAnalysisFileName, "", false);
        }
    }

    protected void updateBest(OptimizationProblem problem, List<Solution> solutions)
    {
        for (Solution s:solutions)
            updateBest(problem,s);
    }
    protected synchronized void updateBest(OptimizationProblem problem , Solution solution)
    {
        if ( bestSolution ==null|| problem.objectiveType().betterThan(solution.objectiveValue(),bestSolution.objectiveValue()))
        {
            bestSolution = solution;
           // System.out.println("BEST UPDATED : "+ bestSolution);
            bestAchieveTime = System.currentTimeMillis();
            if (!convergeAnalysisFileName.isEmpty())
            {
                FileUtils.writeToFile(convergeAnalysisFileName, (bestAchieveTime-startTime)+"   "+ bestSolution.objectiveValue()+"\n", true);
            }
        }
    }


    protected abstract void _perform(OptimizationProblem problem);


}
