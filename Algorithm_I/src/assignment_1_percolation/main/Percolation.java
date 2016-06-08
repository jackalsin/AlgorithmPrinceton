package assignment_1_percolation.main;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author jacka
 * @version 1.0 on 6/7/2016.
 */
public class Percolation {
    private final WeightedQuickUnionUF blocks;
    private final WeightedQuickUnionUF fullness;
    private final int rows;
    private final boolean[] openness;
    private final int startIndex = 0;
    private final int tailIndex;
    /**
     * Creating an N by N grid with all sites blocked.
     * @param N size of the grids.
     */
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("N cannot be less than or equals 0, N =" + N);
        blocks = new WeightedQuickUnionUF(N * N + 2);
        fullness = new WeightedQuickUnionUF(N * N + 2);
        rows = N;
        openness = new boolean[N * N + 2];
        tailIndex = N * N + 1;
        openness[startIndex] = true;
        openness[tailIndex] = true;

        for (int col = 1; col <= N; col++) {
            // set zero row to connect to startIndex
            int zeroRowIndex = getIndex(1, col);
            blocks.union(zeroRowIndex, startIndex);
            fullness.union(zeroRowIndex, startIndex);
            // set last row to connect to tailIndex
            int lastRowIndex = getIndex(N, col);
            blocks.union(lastRowIndex, tailIndex);
        }
    }

    /**
     * Open site (i, j) if it's not opened yet
     * @param i row number
     * @param j column number
     */
    public void open(int i, int j) {
        if (isValidIndex(i, j)) {
            openness[getIndex(i, j)] = true;
            openHelper(i, j, 0, -1);
            openHelper(i, j, 0, +1);
            openHelper(i, j, -1, 0);
            openHelper(i, j, +1, 0);

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    private void openHelper(int i, int j, int dx, int dy) {
        int tgtRow = i + dx;
        int tgtCol = j + dy;
        int tgtIndex = getIndex(tgtRow,  tgtCol);
        int oriIndex = getIndex(i, j);
        if (isValidIndex(tgtRow, tgtCol) && openness[tgtIndex]) {
            blocks.union(oriIndex, tgtIndex);
            fullness.union(oriIndex, tgtIndex);
        }
    }
    private int getIndex(int i, int j) {
        return (i - 1) * rows + j;
    }

    private boolean isValidIndex(int i, int j) {
        return (i > 0 && j > 0 && i <= rows && j <= rows);
    }

    /**
     * is site (row i, column j) open?
     * @param i row number
     * @param j column number
     * @return true when it's open
     */
    public boolean isOpen(int i, int j) {
        if (isValidIndex(i, j)) {
            return openness[getIndex(i, j)];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * is the site full
     * @param i row number
     * @param j column number
     * @return true when the site is full.
     */
    public boolean isFull(int i, int j) {
        if (isValidIndex(i, j))
            return fullness.connected(startIndex, getIndex(i, j)) && isOpen(i, j);
        else
            throw new IndexOutOfBoundsException();
    }

    /**
     * does the system percolate?
     * @return true when it percolates
     */
    public boolean percolates() {
        if (rows == 1) return isOpen(1,1);
        return blocks.connected(startIndex, tailIndex);
    }

    public static void main(String[] args)  {
        Percolation percolation = new Percolation(1);
        StdOut.println(percolation.percolates());

        percolation.open(1,1);
        StdOut.println(percolation.percolates());

        percolation = new Percolation(2);
        StdOut.println(percolation.percolates());

        percolation.open(1,1);
        percolation.open(1,2);

        StdOut.println(percolation.percolates());
    }
}
