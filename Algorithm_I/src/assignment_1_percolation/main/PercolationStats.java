package assignment_1_percolation.main;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author jacka
 * @version 1.0 on 6/8/2016.
 */
public class PercolationStats {
    private double[] results ;
    private int T;
    /**
     * // perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        if (T <= 0 || N <= 0) throw new IllegalArgumentException();
        results = new double[T];
        this.T = T;
        int currentTimes = 0;
        while (currentTimes < T) {
            results[currentTimes] = (double) experimentOnce(N) / (double) N;
        }
    }

    private int experimentOnce(int N) {
        Percolation percolation = new Percolation(N);
        int openSites = 0;
        while (!percolation.percolates()) {
            int i = StdRandom.uniform(1, N);
            int j = StdRandom.uniform(1, N);
            if (!percolation.isOpen(i, j)) {
                percolation.open(i, j);
                openSites++;
            }
        }
        return openSites;
    }
    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev()  {
        return StdStats.stddev(results);
    }
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }             // low  endpoint of 95% confidence interval
    public double confidenceHi()           {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }   // high endpoint of 95% confidence interval

    public static void main(String[] args) {

    }
}
