package com.hirube.talons.sensor;

/**
 * Created by Rube on 17/1/16.
 */
public class ExponentiallyWeightedSmoother {
    private double alpha;
    private int exponent;
    private int n;

    public ExponentiallyWeightedSmoother(double alpha, int exponent) {
        this.alpha = alpha;
        this.exponent = exponent;
        n = 3;
    }

    public ExponentiallyWeightedSmoother(double alpha, int exponent, int n) {
        this.alpha = alpha;
        this.exponent = exponent;
        this.n = n;
        this.last = new double[n];
        this.current = new double[n];
    }

    private double[] last = new double[3];
    private double[] current = new double[3];

    public double[] changed(Double[] values) {
        for (int i = 0; i < n; ++i) {
            last[i] = current[i];
            double diff = values[i] - last[i];
            double correction = diff * alpha;
            for (int j = 1; j < exponent; ++j) {
                correction *= Math.abs(diff);
            }
            if (correction > Math.abs(diff) ||
                    correction < -Math.abs(diff)) correction = diff;
            current[i] = last[i] + correction;
        }
        return current;
    }
}

