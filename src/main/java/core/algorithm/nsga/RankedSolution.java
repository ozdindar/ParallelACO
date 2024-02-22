package core.algorithm.nsga;


import core.algorithm.SimpleSolution;
import core.base.Representation;
import core.base.Solution;

public class RankedSolution extends SimpleSolution {
    int rank;
    boolean ranked;

    public RankedSolution(SimpleSolution solution) {
        super(solution.getRepresentation(),solution.objectiveValues());
        ranked= false;
    }

    public RankedSolution(RankedSolution other) {
        super(other.getRepresentation(),other.objectiveValues());
        ranked= other.ranked;
        rank = other.rank;
    }

    public RankedSolution(Representation rep, double[] objectives) {
        super(rep, objectives);
        ranked= false;
    }

    void setRank(int rank)
    {
        this.rank = rank;
        ranked=true;
    }

    int rank()
    {
        return rank;
    }

    @Override
    public String toString() {
        return super.toString()+ " R:"+ rank;
    }

    @Override
    public Solution clone() {
        return new RankedSolution(this);
    }


}
