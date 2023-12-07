package core.base;

public interface Representation {
    int hashCode();
    boolean equals(Object obj);
    Representation clone();
}
