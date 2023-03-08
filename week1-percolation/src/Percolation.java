import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] sites;
    private int numOpenSites;
    private int gridSize;
    private WeightedQuickUnionUF weightedQuickUnionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        sites = new boolean[n + 1][n + 1];
        numOpenSites = 0;
        gridSize = n;
        int virtualTopSite = 0;
        int virtualLowerSite = gridSize * gridSize + 2;

        weightedQuickUnionUF = new WeightedQuickUnionUF(gridSize * gridSize + 2);

        // sites[0][0] = true;

        for (int i = 1; i <= gridSize; i++) {
            weightedQuickUnionUF.union(virtualTopSite, i);
        }

        for (int i = gridSize; i > 0; i--) {
            weightedQuickUnionUF.union(virtualLowerSite - 1, virtualLowerSite - 1 - i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        validate(row, col);
        if (!isOpen(row, col)) {
            sites[row][col] = true;
            numOpenSites++;
            int currentSite1d = xyTo1d(row, col);

            if (row != gridSize && isOpen(row + 1, col)) {
                int adjacentSite1d = xyTo1d(row + 1, col);
                weightedQuickUnionUF.union(currentSite1d, adjacentSite1d);
            }

            if (row != 1 && isOpen(row - 1, col)) {
                int adjacentSite1d = xyTo1d(row - 1, col);
                weightedQuickUnionUF.union(currentSite1d, adjacentSite1d);
            }

            if (col != gridSize && isOpen(row, col + 1)) {
                int adjacentSite1d = xyTo1d(row, col + 1);
                weightedQuickUnionUF.union(currentSite1d, adjacentSite1d);
            }

            if (col != 1 && isOpen(row, col - 1)) {
                int adjacentSite1d = xyTo1d(row, col - 1);
                weightedQuickUnionUF.union(currentSite1d, adjacentSite1d);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        boolean isFull = false;
        validate(row, col);
        int q = xyTo1d(row, col);
        isFull = (weightedQuickUnionUF.find(0) == weightedQuickUnionUF.find(q)) && isOpen(row, col);

        return isFull;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        boolean percolates;
        int q = gridSize * gridSize + 2;
        percolates = (weightedQuickUnionUF.find(0) == weightedQuickUnionUF.find(q - 1));
        if (gridSize == 1) {
            percolates = isOpen(1, 1);
        }
        if (gridSize == 2) {
            percolates = (isOpen(1, 1) && isOpen(2, 1)) || (isOpen(1, 2) && isOpen(2, 2));
        }
        return percolates;
    }

    // helper methods
    private int xyTo1d(int row, int column) {
        return ((row - 1) * gridSize) + column;
    }

    // validate that coordinates are within range
    private void validate(int row, int column) {

        if (row < 1 || row > gridSize) {
            throw new IllegalArgumentException("row " + row + " is not between 1 and " + gridSize);
        }

        if (column < 1 || column > gridSize) {
            throw new IllegalArgumentException(
                    "column " + column + " is not between 1 and " + gridSize);
        }

    }


    public static void main(String[] args) {
        System.out.println("enter gridsize: ");
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);

        if (!percolation.percolates()) {
            System.out.println("it doesnt percolate yet");
        } else {
            System.out.println("percolates!!");
        }

        percolation.open(1, 1);
        System.out.println("row 1 col 1 succesfully openned");

        if (!percolation.percolates()) {
            System.out.println("it doesnt percolate yet");
        } else {
            System.out.println("percolates!!");
        }

        if (percolation.isFull(1, 1)) {
            System.out.println("site 1, 1 is full");
        } else {
            System.out.println("site 1, 1 is not full");
        }

        percolation.open(2, 1);
        System.out.println("row 2, col 1 successfully openned");
        if (!percolation.percolates()) {
            System.out.println("it doesnt percolate yet");
        } else {
            System.out.println("percolates!!");
        }

        if (percolation.isFull(2, 1)) {
            System.out.println("site 2, 1 is full");
        } else {
            System.out.println("site 2, 1 is not full");
        }
    }

}
