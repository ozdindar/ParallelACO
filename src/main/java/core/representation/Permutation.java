package core.representation;

import core.base.Representation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Permutation implements Representation {

    int nodes[];

    public Permutation(int[] nodes) {
        this.nodes = nodes;
    }

    public Permutation(int n) {
        nodes = IntStream.range(0,n).toArray();
    }

    public Permutation(List<Integer> list) {
        nodes = list.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override
    public Representation clone() {
        return new Permutation(Arrays.copyOf(nodes,nodes.length));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(nodes);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Permutation))
            return false;
        return Arrays.equals(nodes,((Permutation) obj).nodes);
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes);
    }

    public int[] getNodes() {
        return nodes;
    }

    public int size() {
        return nodes.length;
    }

    public int get(int i) {
        return nodes[i];
    }

    public void set(int index, int value){
        nodes[index] = value;
    }

    public int last() {
        return nodes[nodes.length-1];
    }

    public void swap(int firstIndex, int secondIndex){
        int temp = nodes[firstIndex];
        nodes[firstIndex] = nodes[secondIndex];
        nodes[secondIndex] = temp;
    }
}
