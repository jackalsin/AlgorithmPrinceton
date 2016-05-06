package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is the quick find algorithm for the dynamic connect
 *
 * @author jacka
 * @version 1.0.0 on 5/5/2016.
 */
public class QuikFind {

    public QuikFind(String filePath) {
        try(BufferedReader bufferedReader = new  BufferedReader(new FileReader(filePath))) {
            String headLine = bufferedReader.readLine();
            int totalNodes = Integer.parseInt(headLine.trim());
            // init the array
            int[] allNodes = new int[totalNodes];
            for (int i = 0; i < allNodes.length; i++) {
                allNodes[i] = i;
            }
            String nextLine = null;
            while ((nextLine = bufferedReader.readLine()) != null) {
                String[] items = nextLine.split(" ");
                int left = Integer.parseInt(items[0]);
                int right = Integer.parseInt(items[1]);
                allNodes[left] = allNodes[right];
            }
            


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
