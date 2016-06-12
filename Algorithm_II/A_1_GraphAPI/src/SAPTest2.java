import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class SAPTest2 {
    private SAP sap;
    @org.junit.Before
    public void setUp() throws Exception {
        In in = new In("data\\digraph2.txt");
        Digraph G = new Digraph(in);
        sap = new SAP(G);
    }


    @Test
    public void lengthTest_2_0() {
        assertEquals(sap.length(2, 0), 4);
    }

    @Test
    public void lengthTest_1_4() {
        assertEquals(sap.length(1, 4), 3);
    }

    @Test
    public void lengthTest_1_5() {
        assertEquals(sap.length(1, 5), 2);
    }
}
