package core.problems.coalitionFormation;

import core.base.ProblemModel;
import core.base.Representation;

import java.io.Serializable;

public abstract class CFPModel implements ProblemModel,Serializable {

    int agents[][];

    protected int InfeasibilityFactor = 1000;

    public CFPModel(int[][] agents) {
        this.agents= agents;
    }

    public int[][]getAgents() {
        return agents;
    }

    public int getAgentCount(){
        return agents.length;
    }

    public int getAbilitiyCount() { return agents[0].length-1;}


    public abstract double cost(Representation r);
    public abstract double costDifference(Representation r,int  agentIndex,  int coalitionIndex);
}
