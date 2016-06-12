import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jacka
 * @version 1.0 on 6/12/2016.
 */
public class SAPRandom1 {
    private SAP sap;
    @org.junit.Before
    public void setUp() throws Exception {
        In in = new In("data\\Random1.txt");
        Digraph G = new Digraph(in);
        sap = new SAP(G);
    }

    @Test
    public void testLength_1_3() {
        assertEquals(2, sap.length(1, 3));
    }

    @Test
    public void testLength_3_1() {
        assertEquals(2, sap.length(3, 1));
    }



}
