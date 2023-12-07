package core.problems.wsn;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WSNProblemGenerator {

    private final int m;
    private final int k;
    private final int communicatingRange;
    private final int sensingRange;
    private final int terminationValue;
    private final double mutationRate;

    public WSNProblemGenerator(Builder builder) {
        this.m = builder.m;
        this.k = builder.k;
        this.communicatingRange = builder.communicatingRange;
        this.sensingRange = builder.sensingRange;
        this.terminationValue = builder.terminationValue;
        this.mutationRate = builder.mutationRate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int m;
        private int k;
        private int communicatingRange;
        private int sensingRange;
        private int terminationValue;
        private double mutationRate;

        public WSNProblemGenerator build() {
            return new WSNProblemGenerator( this );
        }

        public Builder m(int m) {
            this.m = m;
            return this;
        }

        public Builder k(int k) {
            this.k = k;
            return this;
        }

        public Builder communicatingRange(int communicatingRange) {
            this.communicatingRange = communicatingRange;
            return this;
        }

        public Builder sensingRange(int sensingRange) {
            this.sensingRange = sensingRange;
            return this;
        }

        public Builder terminationValue(int terminationValue) {
            this.terminationValue = terminationValue;
            return this;
        }

        public Builder mutationRate(double mutationRate) {
            this.mutationRate = mutationRate;
            return this;
        }
    }

    public WSNOptimizationProblem generateProblemInstance(Point2D[] targets, Point2D[] potentialPositions) {
        WSN wsn = new WSN(
                targets,
                potentialPositions,
                m,
                k,
                communicatingRange,
                sensingRange,
                terminationValue,
                mutationRate
        );
        return new WSNOptimizationProblem(wsn, new WSNMinimumSensorObjective());
    }

    public static WSNOptimizationProblem generateProblemInstanceFromJson(String filePath) {
        return new WSNOptimizationProblem(Experiment.readFromJson(filePath), new WSNMinimumSensorObjective());
    }

    public static Point2D[] generateGrid(Point2D dimensions, Point2D padding, int gridSize) {
        List<Point2D> point2DList = new ArrayList<>();
        int screenX = (int) dimensions.getX();
        int screenY = (int) dimensions.getY();

        for (int i = (int) padding.getX(); i < screenX; i += gridSize) {
            for (int j = (int) padding.getY(); j < screenY; j += gridSize) {
                point2DList.add(new Point2D(i, j));
            }
        }
        Point2D[] point2DArray = new Point2D[point2DList.size()];
        point2DList.toArray(point2DArray);

        return point2DArray;
    }

    public static Point2D[] generateRandomPoint2D(Point2D dimensions, int elementCount) {
        Random random = new SecureRandom();
        Point2D[] point2DArray = new Point2D[elementCount];
        int screenX = (int) dimensions.getX();
        int screenY = (int) dimensions.getY();

        for (int i = 0; i < elementCount; i++) {
            int x = random.nextInt(screenX);
            int y = random.nextInt(screenY);
            point2DArray[i] = new Point2D(x, y);
        }

        return point2DArray;
    }



}
