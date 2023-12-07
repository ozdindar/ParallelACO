package core.experiments;


import core.algorithm.AbstractMetaheuristic;
import core.base.OptimizationProblem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dindar.oz on 19.06.2015.
 */
public class RepeatingMetaHeuristicEngine implements Runnable {

    private final OptimizationProblem problem;

    private final AbstractMetaheuristic metaHeuristic;

    private final String prefix;
    private final String outputFileName;
    private String suffix= "";

    int repeatCount =10; // Default 10

    public RepeatingMetaHeuristicEngine(AbstractMetaheuristic metaheuristic, OptimizationProblem problem, String outputFileName, String prefix, String suffix, int repeatCount) {
        this(metaheuristic,problem,outputFileName,prefix,suffix);
        this.repeatCount = repeatCount;
    }

    public RepeatingMetaHeuristicEngine(AbstractMetaheuristic metaheuristic, OptimizationProblem problem, String outputFileName, String prefix, String suffix) {
        this(metaheuristic,problem,outputFileName,prefix);
        this.suffix = suffix;
    }

    public RepeatingMetaHeuristicEngine(AbstractMetaheuristic metaheuristic, OptimizationProblem problem, String outputFileName, String prefix) {
        this.metaHeuristic = metaheuristic;
        this.problem = problem;
        this.outputFileName = outputFileName;
        this.prefix= prefix;
    }

    private synchronized void saveResult(String result)
    {
        if (outputFileName ==null || result == null || result.isEmpty())
            return;

        try {
            File outputFile = new File(outputFileName);
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFile,true));
            outputWriter.write(result);
            outputWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(RepeatingMetaHeuristicEngine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run()
    {
        long bestAchieveTime =0;
        long tStart = System.currentTimeMillis();
        double totalCost =0;

        for (int i=0;i<repeatCount;i++) {
            long tdStart = System.currentTimeMillis();
            metaHeuristic.perform(problem);
            totalCost += metaHeuristic.getBestSolution().objectiveValue();
            bestAchieveTime += (metaHeuristic.getBestAchieveTime()-tdStart);
        }
        long tDelta = System.currentTimeMillis() - tStart;
        double elapsedTime = tDelta / (repeatCount*1000.0);
        totalCost /=repeatCount;
        double bestAchieveTimeD = (double)bestAchieveTime/(repeatCount*1000.0);

        String resultStR = ""+ totalCost;
        if (resultStR != null) {
            resultStR = prefix + " " + bestAchieveTimeD + " " + elapsedTime + " " + resultStR + " " + suffix + "\n";
            saveResult(resultStR);
            System.out.print(resultStR);
        }
    }
}
