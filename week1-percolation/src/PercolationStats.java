import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] results;
    private int trials;
    private int n;
    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.trials = trials;
        this.n = n;
        results = new double[trials];
        int i = 0;
        while (i < trials) {
            Percolation percolationExperiment = new Percolation(n);
            double counter = 0.0;
            while (!percolationExperiment.percolates()) {
                int index = StdRandom.uniformInt(1, n * n + 1);
                int[] coordinates = toXY(index);
                if (!percolationExperiment.isOpen(coordinates[0], coordinates[1])) {
                    percolationExperiment.open(coordinates[0], coordinates[1]);
                    counter++;
                }
            }
            results[i] = counter / (n * n);
            i++;
        }
    }

    // helper
    private int[] toXY(int index) {
        int row = (index - 1) / n + 1;
        int column = (index - 1) % n + 1;
        return new int[]{row, column};
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println("Mean: " + percolationStats.mean());
        System.out.println("Std deviation: " + percolationStats.stddev());
    }
}
