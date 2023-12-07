package core.algorithm.localsearch;

import core.algorithm.SimpleSolution;
import core.base.OptimizationProblem;
import core.base.Representation;
import core.base.Solution;
import core.representation.Permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: TBA
 */
public class PermutationSwapIterator implements NeighboringIterator {

    private int firstIndex;
    private int secondIndex;
    private boolean isFinished;

    public PermutationSwapIterator() {
        reset();
        isFinished = false;
    }

    @Override
    public void reset() {
        firstIndex = 0;
        secondIndex = 1;
    }

    @Override
    public Solution nextNeighbor(OptimizationProblem problem, Solution solution) {

        if (isFinished){
            return null;
        }

        Permutation permutation = (Permutation) solution.getRepresentation().clone();
        int size = permutation.size();
        int[] nodes = permutation.getNodes();
        swap(nodes,firstIndex,secondIndex);
        updateIndexes(size);
        Representation representation = new Permutation(nodes);
        Solution neighbor = new SimpleSolution(representation,problem.objectiveValues(representation));
        return neighbor;
    }

    private void updateIndexes(int size) {
        secondIndex++;
        if(secondIndex == (size)){
            firstIndex++;
            secondIndex = firstIndex+1;
        }

        if(firstIndex == (size-1)){
            reset();
            isFinished = true;
        }
    }

    private void swap(int[] nodes, int firstIndex, int secondIndex) {
        int temp = nodes[firstIndex];
        nodes[firstIndex] = nodes[secondIndex];
        nodes[secondIndex] = temp;
    }

    @Override
    public List<Solution> allNeighbors(OptimizationProblem problem, Solution solution) {
        List<Solution> neighbors = new ArrayList<>();
        reset();
        neighbors.add(nextNeighbor(problem,solution));
        while (firstIndex != 0){
            neighbors.add(nextNeighbor(problem,solution));
        }
        return neighbors;
    }
}
