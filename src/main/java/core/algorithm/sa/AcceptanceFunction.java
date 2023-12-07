package core.algorithm.sa;

public interface AcceptanceFunction {
    double ratio(double deltaF, double temperature);
}
