package core.problems.coalitionFormation;

import core.base.Representation;
import core.representation.IntegerAssignment;

import java.util.Arrays;

public class MultiCoalition implements Representation {

    public static final int COST = 0;
    int coalitionCount;
    IntegerAssignment coalitionAssignment;
    int totalAbilities[][];
    int[][] agents;

    public MultiCoalition(IntegerAssignment coalitionAssignment, int[][] agents, int coalitionCount) {
        this.coalitionAssignment = coalitionAssignment;
        this.agents = agents;
        this.coalitionCount= coalitionCount;

    }

    public MultiCoalition clone()
    {
        int[][] newAgents =new  int[agents.length][agents[0].length];
        for (int i = 0; i < newAgents.length; i++) {
            for (int j = 0; j < newAgents[i].length; j++) {
                newAgents[i][j]= agents[i][j];
            }
        }
        return new MultiCoalition( (IntegerAssignment) coalitionAssignment.clone(),newAgents,coalitionCount);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MultiCoalition))
            return false;

        MultiCoalition other = (MultiCoalition) o;

        if (!coalitionAssignment.equals(other.coalitionAssignment))
            return false;

        if (coalitionCount != other.coalitionCount)
            return false;

        return Arrays.deepEquals(agents,other.agents);

    }


    public double distanceTo(Representation r) {
        int d =0;
        MultiCoalition other= (MultiCoalition) r;

        for (int a = 0; a < agents.length; a++) {
            if ( coalitionAssignment.get(a)!= other.coalitionAssignment.get(a))
                d++;
        }


        return d;
    }

    private void calculateAbilities() {


        totalAbilities = new int[coalitionCount+1][agents[0].length];

        for (int coalition = 0; coalition <totalAbilities.length ; coalition++) {
            for(int a = 0 ; a<agents.length ; a++){
                if(coalitionAssignment.get(a)==coalition){

                    totalAbilities[coalition][COST] += agents[a][COST] ;
                    addAbilities(totalAbilities[coalition], agents[a]);
                }
            }
        }

    }

    public int[] getTotalAbilities(int coalitionIndex)
    {

        if (totalAbilities == null)
            calculateAbilities();

        return totalAbilities[coalitionIndex];
    }

    public void reassign(int agentIndex, int coalitionIndex)
    {
        if (totalAbilities== null)
            calculateAbilities();

        int oldCoalition = coalitionAssignment.get(agentIndex);

        totalAbilities[oldCoalition][COST] -= agents[agentIndex][COST];
        removeAbilities(totalAbilities[oldCoalition],agents[agentIndex]);

        totalAbilities[coalitionIndex][COST] += agents[agentIndex][COST];
        addAbilities(totalAbilities[coalitionIndex],agents[agentIndex]);

        coalitionAssignment.set(agentIndex,coalitionIndex);

    }

    private void addAbilities(int[] totalAbility, int[] agent) {
        for(int i = 1 ; i<agent.length ; i++){
            totalAbility[i] += agent[i];
        }
    }

    private void removeAbilities(int[] totalAbility, int[] agent) {
        for(int i = 1 ; i<agent.length ; i++){
            totalAbility[i] -= agent[i];
        }
    }

    public int get(int agentIndex)
    {
        return coalitionAssignment.get(agentIndex);
    }

    public int length()
    {
        return agents.length;
    }

    public String toString(){
        if (totalAbilities == null)
            calculateAbilities();

        String st = "";
        st += coalitionAssignment;
        for (int c = 1; c < coalitionCount; c++) {
            st += " Total Abilities " + Arrays.deepToString(totalAbilities)+"\n";
        } ;
        return st;
    }

    public IntegerAssignment getCoalitionAssignment() {
        return coalitionAssignment;
    }

    public int getCoalitionCount() {
        return coalitionCount;
    }
}
