package core.problems.coalitionFormation;

import core.base.Representation;
import core.exceptions.InvalidIndividual;

import java.io.*;
import java.util.Arrays;

import static core.problems.coalitionFormation.Coalition.COST;

public class MCFPModel extends CFPModel implements Serializable {


    int[][] requiredAbilities; // Required abilities of each task  [task][ability]

    public MCFPModel(int[][] agents, int[][] taskRequirements) {
        super(agents);
        requiredAbilities = taskRequirements;
    }

    @Override
    public String toString() {
        String st = "";

        for (int i = 0; i < agents.length; i++) {
            st+= "A-"+(i+1) + " ("+agents[i][COST]+")" + Arrays.toString(Arrays.copyOfRange(agents[i],1,agents[i].length))+"\n";

        }

        for (int t = 0; t < requiredAbilities.length; t++) {
            st+= "TASK-"+t+":"+Arrays.toString(requiredAbilities)+"\n";
        }

        return st;
    }

    @Override
    public boolean isFeasible(Representation r) {

        if (!(r instanceof MultiCoalition))
            throw new InvalidIndividual("MTMCFP accepts only MultiCoalition");

        MultiCoalition coalitions = (MultiCoalition) r;

        for (int c = 1; c <requiredAbilities.length ; c++) {
            for (int a = 0; a < getAbilityCount(); a++) {

                int totalAbilities[]= coalitions.getTotalAbilities(c);
                if (totalAbilities[a]< requiredAbilities[c][a]) {
                    return false;

                }

            }
        }



        return true;
    }

    @Override
    public double cost(Representation r) {
        if (!(r instanceof MultiCoalition))
            throw new InvalidIndividual("MTMCFP accepts only MultiCoalition");

        MultiCoalition coalitions = (MultiCoalition) r;

        double cost =0;
        for (int c = 1; c < coalitions.getCoalitionCount(); c++) {
            cost += coalitions.getTotalAbilities(c)[COST];
            cost += infeasibilityCost(c,coalitions.getTotalAbilities(c));
        }

        return cost;
    }



    private int infeasibilityCost(int coalition, int[] totalAbility) {
        int infeasibility = 0;
        for(int i = 0; i< getAbilitiyCount() ; i++){
            if(requiredAbilities[coalition][i] > totalAbility[i+1]){
                infeasibility += InfeasibilityFactor* (requiredAbilities[coalition][i] - totalAbility[i+1] );
            }
        }
        return infeasibility;
    }

    private int getAbilityCount() {
        return requiredAbilities[0].length;
    }


   /* public double costDifference(Representation r, int agentIndex, int coalitionIndex) {

        MultiCoalition coalitions = (MultiCoalition) r.clone();
        int costDiff = 0;
        int oldCoalition = coalitions.get(agentIndex);

        if(oldCoalition == coalitionIndex){
            return 0;
        }

        coalitions.reassign(agentIndex,coalitionIndex);

        if (coalitionIndex ==0) // agent becomes unused
            costDiff -= agents[agentIndex][COST];
        else if (oldCoalition==0) // agent starts to be used
            costDiff += agents[agentIndex][COST];

        if (oldCoalition != 0)
            costDiff += infeasibilityDifferenceCost(coalitions.getTotalAbilities(oldCoalition), true, agentIndex, requiredAbilities[oldCoalition]);
        if (coalitionIndex != 0)
            costDiff += infeasibilityDifferenceCost(coalitions.getTotalAbilities(coalitionIndex), false, agentIndex,requiredAbilities[coalitionIndex]);


        return costDiff;
    } */

    public double costDifference(Representation r, int agentIndex, int coalitionIndex) {  // removed clone

        MultiCoalition coalitions = (MultiCoalition) r;
        int costDiff = 0;
        int oldCoalition = coalitions.get(agentIndex);

        if(oldCoalition == coalitionIndex){
            return 0;
        }

        coalitions.reassign(agentIndex,coalitionIndex);

        if (coalitionIndex ==0) // agent becomes unused
            costDiff -= agents[agentIndex][COST];
        else if (oldCoalition==0) // agent starts to be used
            costDiff += agents[agentIndex][COST];

        if (oldCoalition != 0)
            costDiff += infeasibilityDifferenceCost(coalitions.getTotalAbilities(oldCoalition), true, agentIndex, requiredAbilities[oldCoalition]);
        if (coalitionIndex != 0)
            costDiff += infeasibilityDifferenceCost(coalitions.getTotalAbilities(coalitionIndex), false, agentIndex,requiredAbilities[coalitionIndex]);


        coalitions.reassign(agentIndex,oldCoalition);

        return costDiff;
    }



    private int infeasibilityDifferenceCost(int[] totalAbility, boolean bit, int agent, int requiredAbilities[]) {
        int infeasibility = 0;
        if(bit){
            for(int i = 0; i< getAbilitiyCount() ; i++){
                if(totalAbility[i+1]< requiredAbilities[i])
                {
                    if (requiredAbilities[i]-totalAbility[i+1] <agents[agent][i+1])
                    {
                        infeasibility += InfeasibilityFactor * (requiredAbilities[i]-totalAbility[i+1]);
                    }
                    else
                    {
                        infeasibility += agents[agent][i+1]*InfeasibilityFactor;
                    }
                }
            }
        }
        else{
            for(int i = 0; i< getAbilitiyCount() ; i++){
                if(totalAbility[i+1]< requiredAbilities[i])
                    infeasibility -= agents[agent][i+1]*InfeasibilityFactor;
                else {
                    if (totalAbility[i+1]- requiredAbilities[i] <agents[agent][i+1])
                        infeasibility -= InfeasibilityFactor* ( agents[agent][i+1] -totalAbility[i+1]+ requiredAbilities[i]);
                }
            }
        }
        return infeasibility;
    }


    public int[] getRequiredAbilities(int coalition) {
        return Arrays.copyOf(requiredAbilities[coalition], requiredAbilities[coalition].length);
    }

    public int[][] getRequiredAbilities() {
        return requiredAbilities;
    }

    public int getTaskCount(){
        return requiredAbilities.length;
    }

    public void writeToFile(String fileName) throws IOException {
        FileOutputStream fout = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(this);
    }

    public static MCFPModel readFromFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream streamIn = new FileInputStream(fileName);
        ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);

        MCFPModel p = (MCFPModel) objectinputstream.readObject();
        return p;
    }


}
