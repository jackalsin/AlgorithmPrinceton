import edu.princeton.cs.algs4.DoublingRatio;
import edu.princeton.cs.algs4.Picture;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author jacka
 * @version 1.0 on 6/17/2016.
 */
public class SeamCarver {

    private Picture picture;

    private double[][] energy;
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new NullPointerException("picture should not be null");
        this.picture = picture;
        energy = new double[picture.width()][picture.height()];
        for (int col = 0; col < picture.width(); col++) {
            for (int row = 0; row < picture.height(); row++) {
                energy[col][row] = energy(col, row);
            }
        }
    }
    // current picture
    public Picture picture() {
        return picture;
    }
    // width of current picture
    public int width() {
        return picture.width();
    }
    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= picture.width())
            throw new IndexOutOfBoundsException("x should be [0, " + picture.width()
                                                        + "), but x = " + x);
        if (y < 0 || y >= picture.height())
            throw new IndexOutOfBoundsException("y should be [0, " + picture.height()
                                                        + "), but y = " + y);
        if (x == 0 || (x == picture.width() - 1) || (y == 0) || (y == picture.height() - 1)) {
            return 1000d;
        } else {
            Color colorXYLeft = picture.get(x - 1, y);
            Color colorXYRight = picture.get(x + 1, y);
            Color colorXYUp = picture.get(x, y - 1);
            Color colorXYDown = picture.get(x, y + 1);
            double deltaXSquare = Math.pow(colorXYLeft.getRed() - colorXYRight.getRed(), 2) +
                                          Math.pow(colorXYLeft.getGreen() - colorXYRight.getGreen(), 2) +
                                          Math.pow(colorXYLeft.getBlue() - colorXYRight.getBlue(), 2);
            double deltaYSquare = Math.pow(colorXYUp.getRed() - colorXYDown.getRed(), 2) +
                                          Math.pow(colorXYUp.getGreen() - colorXYDown.getGreen(), 2) +
                                          Math.pow(colorXYUp.getBlue() - colorXYDown.getBlue(), 2);
            return Math.sqrt(deltaXSquare + deltaYSquare);
        }
    }
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        int[] result = new int[picture.width()];
        return result;
    }
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int width = picture.width();
        int height = picture.height();
        int[] result = new int[picture.height()];
        double[][] distTo = new double[height][width];
        int[][] edgeTo = new int[height][width];

        double minSum = Double.MAX_VALUE;
        int minSumCol = 0;
        // initialize distTo
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                distTo[row][col] = Double.MAX_VALUE;
            }
        }

        int[] loopDelta = new int[]{-1, 0, 1};
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row == 0) {
                    distTo[row][col] = energy[col][row];
                    edgeTo[row][col] = Integer.MAX_VALUE;
                }
                if (row == height() - 1) {
                    // last row, find the min
                    if (distTo[row][col] < minSum) {
                        minSum = distTo[row][col];
                        minSumCol = col;
                    }

                } else {
                    for (int delta : loopDelta) {
                        int newCol = col + delta;
                        int newRow = row + 1;
                        if (newCol >= 0 && newCol < width()) {
                            double oldVal = distTo[newRow][newCol];
                            double newVal = distTo[row][col] + energy[newCol][newRow];
                            if (newVal < oldVal) {
                                distTo[newRow][newCol] = newVal;
                                edgeTo[newRow][newCol] = col;
                            }
                        }
                    }
                }
            } // end of col for loop
        } // end for row
        List<Integer> resultList = new ArrayList<>();
        resultList.add(minSumCol);
        for (int row = height - 1; row > 0; row--) {
            int toAdd = edgeTo[row][resultList.get(resultList.size() - 1)];
            resultList.add(toAdd);
        }
        Collections.reverse(resultList);
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }

    private void display(double[][] ddArray) {
        System.out.println();
        for (int row = 0; row < ddArray.length; row++) {
            for (int col = 0; col < ddArray[0].length; col++) {
                String out = String.format("%9.2f", ddArray[row][col]);
                System.out.print(out);
            }
            System.out.println();
        }
    }
    private void display(int[][] ddArray) {
        System.out.println("printing edgeTo");
        for (int row = 0; row < ddArray.length; row++) {
            for (int col = 0; col < ddArray[0].length; col++) {
                String out = String.format("%9d", ddArray[row][col]);
                System.out.print(out);
            }
            System.out.println();
        }
    }
}
