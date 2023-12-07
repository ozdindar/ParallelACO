package core.utils;

public class Pair<F,S>
{
    F first;
    S second;

    private Pair(F f, S s) {
        first = f;
        second = s;
    }

    public static <F,S> Pair makePair(F f, S s)
    {
        return new Pair(f,s);
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }
}
