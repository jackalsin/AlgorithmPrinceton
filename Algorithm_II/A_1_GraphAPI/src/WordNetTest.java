import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import static org.junit.Assert.assertEquals;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class WordNetTest {

    private WordNet wordNet;
    @org.junit.Before
    public void setUp() throws Exception {
        wordNet = new WordNet("data\\synsets.txt", "data\\hypernyms.txt");
    }

    @org.junit.Test
    public void nouns() throws Exception {

    }

    @org.junit.Test
    public void isNoun() throws Exception {

    }

    @org.junit.Test
    public void distance() throws Exception {
        assertEquals(12, wordNet.distance("leaker", "Miocene"));
    }



    @org.junit.Test
    public void sap() throws Exception {

    }

    @org.junit.Test
    public void main() throws Exception {

    }
    
}