package core.representation;


import core.base.Solution;

import java.util.Comparator;

/**
 * Created by dindar.oz on 28.05.2015.
 */
public class CostBasedComparator implements Comparator<Solution> {
    @Override
    public int compare(Solution o1, Solution o2) {
        return Double.compare(o1.objectiveValue(),o2.objectiveValue());
    }
}
