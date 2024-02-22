package core.problems.moop.ctp1;


import core.base.ProblemModel;
import core.base.Representation;
import core.problems.moop.viennette.DoubleVector;

public class CTP1  implements ProblemModel {

    @Override
    public boolean isFeasible(Representation rep) {
        double x = ((DoubleVector)rep).get(0);
        double y = ((DoubleVector)rep).get(1);

        if (x>1 || y>1 || x<0 || y<0)
            return false;

        double f1 = x;
        double f2 = (1+y)*Math.exp(-x/1+y);


        boolean c1 = f2/ 0.858*Math.exp(-0.541*f1)>=1;
        boolean c2 = f2/ 0.728*Math.exp(-0.295*f1)>=1;
        return c1&c2;
    }
}
