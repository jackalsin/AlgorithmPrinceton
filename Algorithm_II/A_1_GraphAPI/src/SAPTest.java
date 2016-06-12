import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class SAPTest {
    private SAP sap;
    @org.junit.Before
    public void setUp() throws Exception {
        In in = new In("data\\digraph1.txt");
        Digraph G = new Digraph(in);
        sap = new SAP(G);
    }


    @Test
    public void lengthTest_3_3() {
        assertEquals(sap.length(3, 3), 0);
    }

    @Test
    public void lengthTest_3_11() {
        assertEquals(sap.length(3, 11), 4);
    }
    @Test
    public void lengthTest_9_12() {
        assertEquals(sap.length(9, 12), 3);
    }
    @Test
    public void lengthTest_7_2() {
        assertEquals(sap.length(7, 2), 4);
    }
    @Test
    public void lengthTest_1_6() {
        assertEquals(sap.length(1,6), -1);
    }

    @Test
    public void ancestorTest_3_3() {
        assertEquals(sap.ancestor(3, 3), 3);
    }

    @Test
    public void ancestorTest_3_11() {
        assertEquals(sap.ancestor(3, 11), 1);
    }
    @Test
    public void ancestorTest_9_12() {
        assertEquals(sap.ancestor(9, 12), 5);
    }
    @Test
    public void ancestorTest_7_2() {
        assertEquals(sap.ancestor(7, 2), 0);
    }
    @Test
    public void ancestorTest_1_6() {
        assertEquals(sap.ancestor(1,6), -1);
    }

    // ------ test of iterable ------

    @Test
    public void lengthTestItr_3_3() {
        Set<Integer> three = new HashSet<>();
        Set<Integer> ele = new HashSet<>();
        three.add(3);
        ele.add(3);
        assertEquals(sap.length(three, ele), 0);
    }
    @Test
    public void lengthTestItr_3_11() {
        Set<Integer> three = new HashSet<>();
        Set<Integer> ele = new HashSet<>();
        three.add(3);
        ele.add(11);
        assertEquals(sap.length(three, ele), 4);
    }
    @Test
    public void lengthTestItr_9_12() {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set1.add(9);
        set2.add(12);
        assertEquals(sap.length(set1, set2), 3);
    }
    @Test
    public void lengthTestItr_7_2() {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set1.add(7);
        set2.add(2);
        assertEquals(sap.length(set1, set2), 4);
    }
    @Test
    public void lengthTestItr_1_6() {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set1.add(1);
        set2.add(6);
        assertEquals(sap.length(set1, set2), -1);
    }

    @Test
    public void ancestorTestLtr_3_11() {
        Set<Integer> three = new HashSet<>();
        Set<Integer> ele = new HashSet<>();
        three.add(3);
        ele.add(11);
        assertEquals(sap.ancestor(three, ele), 1);
    }
    @Test
    public void ancestorTestLtr_9_12() {
        Set<Integer> three = new HashSet<>();
        Set<Integer> ele = new HashSet<>();
        three.add(9);
        ele.add(12);
        assertEquals(sap.ancestor(three, ele), 5);
    }
    @Test
    public void ancestorTestLtr_7_2() {
        Set<Integer> three = new HashSet<>();
        Set<Integer> ele = new HashSet<>();
        three.add(7);
        ele.add(2);
        assertEquals(sap.ancestor(three, ele), 0);
    }
    @Test
    public void ancestorTestLtr_1_6() {
        Set<Integer> three = new HashSet<>();
        Set<Integer> ele = new HashSet<>();
        three.add(1);
        ele.add(6);
        assertEquals(sap.ancestor(three, ele), -1);
    }

}
