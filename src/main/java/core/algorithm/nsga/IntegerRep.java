package core.algorithm.nsga;


import core.base.Representation;

public class IntegerRep implements Representation {
    int val;

    public IntegerRep(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return ""+val;
    }

    @Override
    public Representation clone() {
        return new IntegerRep(val);
    }
}
