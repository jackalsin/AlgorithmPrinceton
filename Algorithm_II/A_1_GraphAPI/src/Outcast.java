import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class Outcast {

    private final WordNet wordNet;
    public Outcast(WordNet wordnet) {
        // constructor takes a WordNet object
        this.wordNet = wordnet;
    }
    public String outcast(String[] nouns) {
        // given an array of WordNet nouns, return an outcast
        int largeSum = 0;
        String largeString = null;
        for (String noun : nouns) {
            int curSum = 0;
            for (String compare : nouns) {
                curSum += wordNet.distance(noun, compare);
            }
            if (curSum > largeSum) {
                largeSum = curSum;
                largeString = noun;
            }
        }
        return largeString;
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