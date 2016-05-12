package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * This is the weighted quick union class.
 * It contains another array that contains the size of the current tree.
 * When doing union, let the small tree become the child rather than the bigger tree.
 *
 * It guarantees the larger tree won't appear in the bottom of the tree,
 *
 * Created by jacka on 5/11/2016.
 */
public class WeightedQuickUnion {

    private int[] size;
    private int[] allNodes;

    public WeightedQuickUnion(String filePath) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            String headLine = bufferedReader.readLine();
            int totalNodes = Integer.parseInt(headLine.trim());
            // init the array
            allNodes = new int[totalNodes];
            size = new int[totalNodes];
            for (int i = 0; i < allNodes.length; i++) {
                allNodes[i] = i;
                size[i] = 1;
            }
            String nextLine = null;
            while ((nextLine = bufferedReader.readLine()) != null) {
                String[] items = nextLine.split(" ");
                int left = Integer.parseInt(items[0]); // left
                int right = Integer.parseInt(items[1]); // right
                /* change the child value to root of the parent */

                /* When comparing size, it should use the root size, which is the current tree size*/
                int leftRoot = root(left);
                int rightRoot = root(right);
                if (leftRoot == rightRoot) continue;
                if (size[leftRoot] < size[rightRoot]) {
                    allNodes[leftRoot] = root(right);
                    size[rightRoot] += size[leftRoot];
                } else {
                    allNodes[rightRoot] = root(left);
                    size[leftRoot] += size[rightRoot];
                }
                System.out.println(Arrays.toString(allNodes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isConnected(final int i, final int j) {
        return root(i) == root(j);
    }

    private int root(int i) {
        while (allNodes[i] != i) {
            i = allNodes[i];
        }
        return i;
    }

    public static void main(String[] args) {
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion("conn2.txt");
        System.out.println(Arrays.toString(weightedQuickUnion.allNodes));
        System.out.println(weightedQuickUnion.isConnected(6, 0));

    }
}
