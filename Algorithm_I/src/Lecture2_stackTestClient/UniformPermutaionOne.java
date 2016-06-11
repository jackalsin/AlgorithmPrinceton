package Lecture2_stackTestClient;

import java.util.Arrays;
import java.util.Random;

/**
 * This generates one uniform permutation.
 * @author jacka
 * @version 1.0 on 6/9/2016
 *          <p>
 *          Created by jacka on 6/9/2016.
 */
public class UniformPermutaionOne {

    public static void main(String[] args) {
        int[] a = {3, 2, 5, 6,8,2,1,9};
        int iterRound = 1;
        while (iterRound <= a.length) {
            /* in iteration i, pick an integer r between 0 and i
             * uniformly at random, swap[i] and [r]*/
            int i = iterRound++;
            int r = new Random().nextInt(i);

            swap(a, i, r);
        }

        System.out.println(Arrays.toString(a));
    }

    private static void swap(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
}
