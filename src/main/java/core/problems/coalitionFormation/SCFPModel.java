package core.problems.coalitionFormation;


import core.base.Representation;
import core.exceptions.InvalidIndividual;

import java.io.*;
import java.util.Arrays;

import static core.problems.coalitionFormation.Coalition.COST;

public class SCFPModel extends CFPModel implements Serializable {


    int[] combinedRequiredAbilities;
    //int[] totalAbility;

    public SCFPModel(int[][] agents, int[][] taskRequirements) {
        super(agents);
        combineTasks(taskRequirements);
    }

    @Override
    public String toString() {
        String st = "";

        for (int i = 0; i < agents.length; i++) {
            st+= "A-"+(i+1) + " ("+agents[i][COST]+")" + Arrays.toString(Arrays.copyOfRange(agents[i],1,agents[i].length))+"\n";

        }

        st+= "TASK:"+Arrays.toString(combinedRequiredAbilities);
        return st;
    }

    private void combineTasks(int taskRequirements[][]) {

            if (taskRequirements.length == 0)
                return;
            combinedRequiredAbilities = new int[taskRequirements[0].length];
            for (int t = 0 ; t<taskRequirements.length;t++) {
                for(int a = 0 ; a<combinedRequiredAbilities.length ; a++){
                    if(combinedRequiredAbilities[a] < taskRequirements[t][a]){
                        combinedRequiredAbilities[a] = taskRequirements[t][a];
                    }
                }
            }

    }


    public boolean isFeasible(Representation r) {

        if (!(r instanceof Coalition))
            throw new InvalidIndividual("STSCFP accepts only Coalition");

        Coalition coalition = (Coalition) r;

        for (int a = 0; a < getAbilityCount(); a++) {
            if (coalition.getTotalAbilities()[a+1]< combinedRequiredAbilities[a])
                return false;
        }

        return true;
    }

    @Override
    public double cost(Representation r) {
        if (!(r instanceof Coalition))
            throw new InvalidIndividual("STSCFP accepts only Coalition");

        Coalition coalition = (Coalition) r;

        double cost = coalition.getTotalAbilities()[COST] + infeasibilityCost(coalition.getTotalAbilities());

        return cost;
    }



    private int infeasibilityCost(int[] totalAbility) {
        int infeasibility = 0;
        for(int i = 0 ; i<combinedRequiredAbilities.length ; i++){
            if(combinedRequiredAbilities[i] > totalAbility[i+1]){
                infeasibility += InfeasibilityFactor* (combinedRequiredAbilities[i] - totalAbility[i+1] );
            }
        }
        return infeasibility;
    }

    private int getAbilityCount() {
        return combinedRequiredAbilities.length;
    }

    public double costDifference(Representation r, int agentIndex, int coalitionIndex) {

        Coalition coalition = (Coalition) r.clone();
        int costDiff = 0;
        boolean bit = coalition.get(agentIndex);
        coalition.flip(agentIndex);
        if(bit){
            costDiff -= agents[agentIndex][COST];
        }
        else {
            costDiff += agents[agentIndex][COST];
        }

        costDiff += infeasibilityDifferenceCost(coalition.getTotalAbilities(), bit, agentIndex);

        coalition.flip(agentIndex);
        return costDiff;
    }



    private int infeasibilityDifferenceCost(int[] totalAbility, boolean bit, int agent) {
        int infeasibility = 0;
        if(bit){
            for(int i = 0 ; i<combinedRequiredAbilities.length ; i++){
                if(totalAbility[i+1]<combinedRequiredAbilities[i])
                {
                    if (combinedRequiredAbilities[i]-totalAbility[i+1] <agents[agent][i+1])
                    {
                        infeasibility += InfeasibilityFactor * (combinedRequiredAbilities[i]-totalAbility[i+1]);
                    }
                    else
                    {
                        infeasibility += agents[agent][i+1]*InfeasibilityFactor;
                    }
                }
            }
        }
        else{
            for(int i = 0 ; i<combinedRequiredAbilities.length ; i++){
                if(totalAbility[i+1]<combinedRequiredAbilities[i])
                    infeasibility -= agents[agent][i+1]*InfeasibilityFactor;
                else {
                    if (totalAbility[i+1]-combinedRequiredAbilities[i] <agents[agent][i+1])
                        infeasibility -= InfeasibilityFactor* ( agents[agent][i+1] -totalAbility[i+1]+ combinedRequiredAbilities[i]);
                }
            }
        }
        return infeasibility;
    }


    public int[] getCombinedRequiredAbilities() {
        return Arrays.copyOf(combinedRequiredAbilities,combinedRequiredAbilities.length);
    }

    public void writeToFile(String fileName) throws IOException {
        FileOutputStream fout = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(this);
    }

    public static SCFPModel readFromFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream streamIn = new FileInputStream(fileName);
        ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);

        SCFPModel p = (SCFPModel) objectinputstream.readObject();
        return p;
    }

    public static void main(String[] args) {
        SCFPModel p = null;
        try {
            p = readFromFile("./data/cfData/cf_20_3_1_0.dat");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(p);
    }
}
