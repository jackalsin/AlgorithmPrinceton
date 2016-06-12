import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jacka
 * @version 1.0 on 6/12/2016.
 */
public class SAPRandom257 {
    private SAP sap;
    @Before
    public void setUp() throws Exception {
        In in = new In("data\\digraph-wordnet.txt");
        Digraph G = new Digraph(in);
        sap = new SAP(G);
    }

    @Test
    public void testLength_70860_18158() {
        assertEquals(14, sap.length(70860, 18158));
    }
    @Test
    public void testLength_18158_70860() {
        assertEquals(14, sap.length(18158, 70860));
    }



}
