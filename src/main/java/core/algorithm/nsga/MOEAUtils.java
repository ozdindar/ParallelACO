package core.algorithm.nsga;


import core.base.OptimizationProblem;
import core.base.ProblemModel;
import core.base.Representation;
import core.base.Solution;

public class MOEAUtils {
    public static <PM extends ProblemModel,R extends Representation>
    boolean dominates(OptimizationProblem problem, Solution s1, Solution s2)
    {
        boolean betterFound = false;
        for (int o=0;o<problem.objectiveCount() ;o++ )
        {
            if (problem.objectiveType(o).betterThan(s2.objectiveValue(o),s1.objectiveValue(o)))
                return false;
            else if (problem.objectiveType(o).betterThan(s1.objectiveValue(o),s2.objectiveValue(o)))
                betterFound = true;
        }
        return betterFound;
    }
}
