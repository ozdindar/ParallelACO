package core.problems.moop.viennette;


import core.base.Representation;

import java.util.Arrays;

public class DoubleVector implements Representation {

    double nodes[];

    public DoubleVector(double[] nodes) {
        this.nodes = nodes;
    }

    @Override
    public Representation clone() {
        return new DoubleVector(Arrays.copyOf(nodes,nodes.length));
    }

    public double get(int index) {
        return nodes[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes);
    }
}
