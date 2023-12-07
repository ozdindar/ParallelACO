package core.representation.base;



import core.base.OptimizationProblem;
import core.base.Solution;
import core.representation.CostBasedComparator;

import java.util.Collection;
import java.util.List;

/**
 * Created by dindar.oz on 25.06.2015.
 */
public interface Population {

    public List<Solution> getIndividuals();
    public int size();
    public Solution get(int i);
    public Population clone();
    public void add(Solution i);
    public void add(List<Solution> i);
    public void add(Solution[] i);
    public void add(Population i);
    public void remove(int i);
    public void remove(Solution i);
    public Solution getBest();
    public double getBestCost();
    public boolean isEmpty();
    public boolean contains(Solution i);

    void removeAll(Collection<Solution> victims);

    void sort(CostBasedComparator comparator);

    Population subPopulation(int i, int i1);

    void clear();

    double[] getCosts();

    boolean checkUp(OptimizationProblem problem);
}
