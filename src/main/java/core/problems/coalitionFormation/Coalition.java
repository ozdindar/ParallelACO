package core.problems.coalitionFormation;


import core.base.Representation;

import java.util.Arrays;
import java.util.BitSet;

public class Coalition implements Representation {

    public static final int COST = 0;
    BitSet coalition;
    int totalAbilities[];
    int[][] agents;

    public Coalition(BitSet coalition, int[][] agents) {
        this.coalition = coalition;
        this.agents = agents;


    }

    public Coalition clone()
    {
        int[][] newAgents =new  int[agents.length][agents[0].length];
        for (int i = 0; i < newAgents.length; i++) {
            for (int j = 0; j < newAgents[i].length; j++) {
                newAgents[i][j]= agents[i][j];
            }
        }
        return new Coalition((BitSet) coalition.clone(),newAgents);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coalition))
            return false;

        Coalition other = (Coalition) o;

        if (!coalition.equals(other.coalition))
            return false;

        return Arrays.deepEquals(agents,other.agents);

    }

    public double distanceTo(Representation r) {
        Coalition c= (Coalition) r;

        BitSet bs = (BitSet) coalition.clone();
        bs.xor(c.coalition);

        return bs.cardinality();
    }

    private void calculateAbilities() {


        totalAbilities = new int[agents[0].length];

        for(int i = 0 ; i<agents.length ; i++){
            if(coalition.get(i)){

                totalAbilities[COST] += agents[i][COST] ;
                addAbilities(totalAbilities, agents[i]);
            }
        }
    }

    public int[] getTotalAbilities()
    {
        if (totalAbilities == null)
            calculateAbilities();

        return totalAbilities;
    }




    public void flip(int index)
    {
        if (totalAbilities== null)
            calculateAbilities();

        boolean bit = coalition.get(index);
        if (bit) {
            totalAbilities[COST] -= agents[index][COST];
            removeAbilities(totalAbilities, agents[index]);
        } else {
            totalAbilities[COST] += agents[index][COST];
            addAbilities(totalAbilities, agents[index]);
        }
        coalition.flip(index);


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

    public boolean get(int index)
    {
        return coalition.get(index);
    }

    public int length()
    {
        return agents.length;
    }

    public String toString(){
        if (totalAbilities == null)
            calculateAbilities();

        String st = "";
        st += coalition + " Total Abilities " + Arrays.toString(totalAbilities);
        return st;
    }

    public BitSet getCoalition() {
        return coalition;
    }
}
