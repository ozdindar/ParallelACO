package core.problems.coalitionFormation;

public class Agent {
    double cost;
    int[] abilities;

    public Agent(double cost, int[] abilities) {
        this.cost = cost;
        this.abilities = abilities;
    }

    public double getCost() {
        return cost;
    }

    public int[] getAbilities() {
        return abilities;
    }

    public int getAbility(int i){
        return abilities[i];
    }


}
