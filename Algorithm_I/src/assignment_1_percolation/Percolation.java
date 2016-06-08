package assignment_1_percolation;

import java.util.Arrays;

/**
 * @author jacka
 * @version 1.0 on 6/7/2016.
 */
public class Percolation {
    private final boolean[][] blocks;

    /**
     * Creating an N by N grid with all sites blocked.
     * @param N size of the grids.
     */
    public Percolation(int N) {
        blocks = new boolean[N][N];
        Arrays.fill(blocks, false);
    }

    /**
     * Open site (i, j) if it's not opened yet
     * @param i row number
     * @param j column number
     */
    public void open(int i, int j) {

    }

    /**
     * is site (row i, column j) open?
     * @param i row number
     * @param j column number
     * @return true when it's open
     */
    public boolean isOpen(int i, int j) {

    }    //

    /**
     * is the site full
     * @param i row number
     * @param j column number
     * @return true when the site is full.
     */
    public boolean isFull(int i, int j) {

    }

    /**
     * does the system percolate?
     * @return true when it percolates
     */
    public boolean percolates() {

    }

    public static void main(String[] args)  {

    }
}
