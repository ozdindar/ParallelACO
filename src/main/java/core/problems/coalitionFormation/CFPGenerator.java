package core.problems.coalitionFormation;


import core.utils.random.RNG;

public class CFPGenerator {

    private static final int MAX = 200;
    private static final double TASK_DIFFICULTY = 0.5;

    public static SCFPModel generateSingleCFProblem(int taskCount, int agentCount, int abilityCount, RNG rng)
    {
        int[][] agents = createAgents(agentCount, abilityCount, rng);
        int[] totalAbilities = calculateTotalAbilities(agents, abilityCount, agentCount);
        int[][] taskRequirements = createTasks(totalAbilities, taskCount, abilityCount, rng);
        SCFPModel scf = new SCFPModel(agents, taskRequirements);
        return scf;
    }

    private static int[][] createAgents(int agentCount, int abilityCount, RNG rng)
    {
        int[][] agents = new int[agentCount][abilityCount+1];
        for(int i = 0 ; i<agentCount ; i++){
            for(int j = 0 ; j<abilityCount+1 ; j++){
                agents[i][j] = rng.randInt(MAX)+1;
            }
        }
        return agents;
    }

    private static int[] calculateTotalAbilities(int[][] agents, int abilityCount, int agentCount) {
        int[] totalAbilities = new int[abilityCount];
        for(int i = 0 ; i<agentCount ; i++){
            for(int j = 0 ; j<abilityCount ; j++){
                totalAbilities[j] += agents[i][j+1];
            }
        }
        return totalAbilities;
    }

    private static int[][] createTasks(int[] totalAbilities, int taskCount, int abilityCount, RNG rng) {
        int[][] taskRequirements = new int[taskCount][abilityCount];
        for(int i = 0 ; i<taskCount ; i++){
            for(int j = 0 ; j<abilityCount ; j++){
                taskRequirements[i][j] = rng.randInt((int) (totalAbilities[j]*TASK_DIFFICULTY));
            }
        }
        return taskRequirements;
    }

}
