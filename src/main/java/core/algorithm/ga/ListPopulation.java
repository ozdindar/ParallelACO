package core.algorithm.ga;

import core.base.OptimizationProblem;
import core.base.Solution;
import core.representation.CostBasedComparator;
import core.representation.base.Population;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListPopulation  implements Population {

    List<Solution> pop;

    public ListPopulation() {
        pop = new ArrayList<>();
    }

    public ListPopulation(List<Solution> solutions) {
        pop = solutions;
    }

    @Override
    public List<Solution> getIndividuals() {
        return pop;
    }

    @Override
    public int size() {
        return pop.size();
    }

    @Override
    public Solution get(int i) {
        return pop.get(i);
    }

    @Override
    public Population clone() {
        Population p = new ListPopulation();
        for (Solution s:pop)
            p.add(s.clone());
        return p;
    }

    @Override
    public void add(Solution i) {
        pop.add(i);
    }

    @Override
    public void add(List<Solution> i) {
        pop.addAll(i);
    }

    @Override
    public void add(Solution[] i) {
        pop.addAll(List.of(i));
    }

    @Override
    public void add(Population i) {
        pop.addAll(i.getIndividuals());
    }

    @Override
    public void remove(int i) {
        pop.remove(i);
    }

    @Override
    public void remove(Solution i) {
        pop.remove(i);
    }

    @Override
    public Solution getBest() {
        return pop.stream().max((x,y)->Double.compare(x.objectiveValue(),y.objectiveValue())).get();
    }

    @Override
    public double getBestCost() {
        return pop.stream().max((x,y)->Double.compare(x.objectiveValue(),y.objectiveValue())).get().objectiveValue();
    }

    @Override
    public boolean isEmpty() {
        return pop.isEmpty();
    }

    @Override
    public boolean contains(Solution i) {
        return pop.contains(i);
    }

    @Override
    public void removeAll(Collection<Solution> victims) {
        pop.removeAll(victims);
    }

    @Override
    public void sort(CostBasedComparator comparator) {
        pop.sort(comparator);
    }

    @Override
    public Population subPopulation(int i, int i1) {
        return new ListPopulation(pop.subList(i,i1));
    }

    @Override
    public void clear() {
        pop.clear();
    }

    @Override
    public double[] getCosts() {
        throw new RuntimeException("NOT IMPLEMENTED");
    }

    @Override
    public boolean checkUp(OptimizationProblem problem) {
        throw new RuntimeException("NOT IMPLEMENTED");
    }
}
