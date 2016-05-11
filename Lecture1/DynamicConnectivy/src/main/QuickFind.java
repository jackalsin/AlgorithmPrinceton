package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is the quick find algorithm for the dynamic connect.
 *
 * @author jacka
 * @version 1.0.0 on 5/5/2016.
 */
public class QuickFind {

    private int[] allNodes = null;

    /**
     * The constructor
     * @param filePath the data path.
     */
    public QuickFind(String filePath) {
        try(BufferedReader bufferedReader = new  BufferedReader(new FileReader(filePath))) {
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
                int left = Integer.parseInt(items[0]);
                int right = Integer.parseInt(items[1]);
                int origin = allNodes[left];
                int tgt = allNodes[right];
                for (int i = 0; i < allNodes.length; i++) {
                    if (allNodes[i] == origin) {
                        allNodes[i] = tgt;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isConnected(int i, int j) {
        return allNodes[i] == allNodes[j];
    }
}
