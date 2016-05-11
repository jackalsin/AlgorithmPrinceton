package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the lazy approach of the quick union
 * It creates an array recording the parent index of this item
 *
 * Union(3, 4) --> 3 is the child, and 4 is the parent.
 *
 * @author jacka
 * @version 1.0 on 5/10/2016
 *          <p>
 *          Created by jacka on 5/10/2016.
 */
public class QuickUnion {

    private int[] allNodes;

    public QuickUnion(String filePath) {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            String headLine = bufferedReader.readLine();
            int totalNodes = Integer.parseInt(headLine.trim());
            // init the array
            allNodes = new int[totalNodes];
            for (int i = 0; i < allNodes.length; i++) {
                allNodes[i] = i;
            }
            String nextLine = null;
            while ((nextLine = bufferedReader.readLine()) != null) {
                String[] items = nextLine.split(" ");
                int child = Integer.parseInt(items[0]); // left
                int parent = Integer.parseInt(items[1]); // right
                /* change the child value to root of the parent */
                allNodes[child] = root(parent);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    } // end of the constructor

    public boolean isConnected(final int i, final int j) {
        return root(i) == root(j);
    }

    private int root(int i) {
        while (allNodes[i] != i) {
            i = allNodes[i];
        }
        return i;
    }
}
