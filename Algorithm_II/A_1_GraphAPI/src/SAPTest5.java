import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class SAPTest5 {
    private SAP sap;
    @org.junit.Before
    public void setUp() throws Exception {
        In in = new In("data\\digraph5.txt");
        Digraph G = new Digraph(in);
        sap = new SAP(G);
    }


    @Test
    public void lengthTest_0_17() {
        assertEquals(sap.length(0, 17), 6);
        assertEquals(sap.ancestor(0, 17), 10);
    }


}
