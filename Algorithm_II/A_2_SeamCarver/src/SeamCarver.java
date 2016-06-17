import edu.princeton.cs.algs4.Picture;

import java.awt.*;

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
            return 195075d;
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
            return deltaXSquare + deltaYSquare;
        }
    }
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        int[] result = new int[picture.width()];
        return result;
    }
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int[] result = new int[picture.height()];
        return result;
    }
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {

    }
    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {

    }
}
