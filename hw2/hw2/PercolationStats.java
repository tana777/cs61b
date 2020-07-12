package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import java.sql.Time;

/** HW 2: Percolation
 *
 *  @author tanagegen 07/10/2020
 *
 */

public class PercolationStats {
    /*
        perform T independent experiments on an N-by-N grid
     */
    private int len;
    private int times;
    private Percolation pl;
    private double[] record;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        len = N;
        times = T;
        pl = pf.make(len);
        record = new double[T];
    }

    /*
        helper method
     */
    private void playGrid() {

        for (int t = 0; t < times; t++) {

            while (!pl.percolates()) {
                int row = StdRandom.uniform(0, len);
                int col = StdRandom.uniform(0, len);
                pl.open(row, col);
            }
            record[t] = pl.numberOfOpenSites() / (len * len);
        }
    }



    /*
        sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(record);
    }

    /*
        sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(record);

    }

    /*
        low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(times);
    }

    /*
        high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(times);
    }

}
