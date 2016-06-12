import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class WordNet1515 {

    private WordNet wordNet;


    @org.junit.Before
    public void setUp() throws Exception {
        wordNet = new WordNet("data\\synsets15.txt", "data\\hypernyms15Path.txt");
    }

    @org.junit.Test
    public void nouns() throws Exception {

    }

    @org.junit.Test
    public void isNoun() throws Exception {

    }

    @org.junit.Test
    public void distance() throws Exception {
        wordNet.distance("a", "b");
    }

    @org.junit.Test
    public void sap() throws Exception {

    }

    @org.junit.Test
    public void main() throws Exception {

    }
    
}