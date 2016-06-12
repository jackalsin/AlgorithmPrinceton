import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class WordNetTest {

    @org.junit.Before
    public void setUp() throws Exception {
        In in = new In("data\\digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
        }
    }

    @org.junit.Test
    public void nouns() throws Exception {

    }

    @org.junit.Test
    public void isNoun() throws Exception {

    }

    @org.junit.Test
    public void distance() throws Exception {

    }

    @org.junit.Test
    public void sap() throws Exception {

    }

    @org.junit.Test
    public void main() throws Exception {

    }
    
}