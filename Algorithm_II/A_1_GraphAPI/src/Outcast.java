import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class Outcast {
    public Outcast(WordNet wordnet) {
        // constructor takes a WordNet object

    }
    public String outcast(String[] nouns) {
        // given an array of WordNet nouns, return an outcast
        return null;
    }
    public static void main(String[] args) {
        // see test client below
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}