package core.problems.wsn;

import java.util.Objects;

public class Point2D {
    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point2D(Point2D other)
    {
        x = other.x;
        y = other.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return x == point2D.x && y == point2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
         return new Point2D(this);
    }

    public double distance(Point2D other) {
        return Math.sqrt( (x-other.x)*(x-other.x) + (y-other.y)*(y-other.y) );
    }
}
