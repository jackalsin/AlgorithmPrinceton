import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class SAPTest9 {
    private SAP sap;

    @org.junit.Before
    public void setUp() throws Exception {
        In in = new In("data\\digraph9.txt");
        Digraph G = new Digraph(in);
        sap = new SAP(G);
    }


    @Test
    public void lengthTest_7_3() {
        assertEquals(sap.length(7, 3), 2);
    }
    @Test
    public void lengthTest_0_1() {
        assertEquals(sap.length(0, 1), 2);
    }

    @Test
    public void ancesterTest_7_3() {
        assertEquals(sap.ancestor(7, 3), 3);
    }

    @Test
    public void ancesterTest_0_1() {
            assertEquals(sap.ancestor(0, 1), 0);
    }
}
