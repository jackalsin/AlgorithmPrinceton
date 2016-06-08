package main;

/**
 * @author jacka
 * @version 1.0 on 5/11/2016
 *          <p>
 *          Created by jacka on 5/11/2016.
 */
public class CacheTest {

    public static void main(String[] args) {
        for (int i = -128; i <256; i++) {
            Integer i1 = i;
            Integer i2 = i;
            System.out.println(new Integer(i) == new Integer(i));

            System.out.println(i1 == i2);
        }

        System.out.println("\n\n");

    }
}
