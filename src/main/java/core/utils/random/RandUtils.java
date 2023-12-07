package core.utils.random;

import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

import java.security.SecureRandom;
import java.util.Random;

public class RandUtils {
    static Random rng = new SecureRandom();
    static RNG r = new SecureRandomRNG();


    public static int randInt(int n) {
        return rng.nextInt(n);
    }

    public static double randDouble() { return rng.nextDouble();}

    public static double randDouble(double lb, double ub) {
        RealDistribution rd = new UniformRealDistribution(lb,ub);
        return rd.sample();
    }

    public static RNG getDefaultRNG() {
        return r;
    }

    public static boolean randBoolean(double p)
    {
        return r.randDouble()<p;
    }

    // Returns the selected index based on the weights(probabilities)
    public static int rouletteSelect(double[] weight) {
        return rouletteSelect(r,weight);
    }

    public static int rouletteSelect(double[] weight, int len)
    {
        return rouletteSelect(r,weight,len);
    }

    public static int rouletteSelect(RNG rng, double[] weight, int len) {
        // calculate the total weight
        double weight_sum = 0;
        for(int i=0; i<len; i++) {
            weight_sum += weight[i];
        }
        // get a random value
        double value = rng.randDouble() * weight_sum;
        // locate the random value based on the weights
        for(int i=0; i<len; i++) {
            value -= weight[i];
            if(value <= 0) return i;
        }
        // only when rounding errors occur
        return len - 1;
    }

    public static int[] roulletteSelectMulti(RNG rng, double[] weights, int count)
    {
        Roulette roulette = new Roulette(rng,weights);
        int indexes[] = new int[count];
        for (int i = 0; i < count; i++) {
            indexes[i] = roulette.spin();
        }
        return indexes;
    }

    // Returns the selected index based on the weights(probabilities)
    public static int rouletteSelect(RNG rng, double[] weight) {
        return rouletteSelect(rng,weight,weight.length);
    }

    public static int rouletteSelectInverse(double[] weight, int end) {
        return rouletteSelectInverse(r,weight,end);
    }

    private static int rouletteSelectInverse(RNG r, double[] weight, int end) {
        // calculate the total weight
        double weight_sum = 0;

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for(int i=0; i<end; i++) {
            weight_sum += weight[i];
            if (weight[i]<min)
                min = weight[i];
            if (weight[i]>max)
                max = weight[i];
        }
        // get a random value
        double value = r.randDouble() * weight_sum;

        double t = min+ max;
        // locate the random value based on the weights
        for(int i=0; i<end; i++) {
            value -= (t-weight[i]);
            if(value <= 0) return i;
        }
        // only when rounding errors occur
        return end - 1;
    }

    public static int rouletteSelectInverse(double[] weight) {
        return rouletteSelectInverse(r,weight);
    }



    // Returns the selected index based on the weights(probabilities)
    public static int rouletteSelectInverse(RNG rng, double[] weight) {
        return rouletteSelectInverse(rng,weight,weight.length);
    }

}
