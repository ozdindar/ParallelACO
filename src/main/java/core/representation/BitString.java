package core.representation;


import core.base.Representation;

import java.security.SecureRandom;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BitString implements Representation {
    int len;

    private BitSet bitSet;
    public BitString(BitSet bitSet, int len) {
        this.bitSet = bitSet;
        this.len = len;
    }

    public BitString(int len) {
        this.bitSet = new BitSet(len);
        this.len = len;
    }

    public HashSet<Integer> ones() {
        return IntStream.range(0, len).
                filter(x -> bitSet.get(x)).
                boxed().
                collect(Collectors.toCollection(HashSet::new));
    }

    public HashSet<Integer> zeros()
    {
        return IntStream.range(0, len).
                filter(x -> !bitSet.get(x)).
                boxed().
                collect(Collectors.toCollection(HashSet::new));
    }

    public String toString() {
        return "["+ bitSet.cardinality()+"] "+ bitSet.toString();
    }

    public void flip(int bitIndex) {
        bitSet.flip(bitIndex);
    }

    public void set(int bitIndex, boolean value) {
        bitSet.set(bitIndex, value);
    }

    public int length() {
        return len;
    }

    public boolean get(int bitIndex) {
        return bitSet.get(bitIndex);
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    @Override
    public int hashCode() {
        return bitSet.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return bitSet.equals(((BitString) obj).bitSet);
    }

    @Override
    public Representation clone() {
        return new BitString((BitSet) bitSet.clone(),len);
    }

    public static BitString random(int len)
    {
        Random r = new SecureRandom();
        BitSet bitSet = new BitSet(len);
        for (int i = 0; i < len; i++) {
            if (r.nextBoolean())
                bitSet.set(i);
        }
        return new BitString(bitSet,len);
    }
}
