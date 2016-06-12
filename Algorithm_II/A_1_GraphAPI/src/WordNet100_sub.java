import static org.junit.Assert.assertEquals;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class WordNet100_sub {

    private WordNet wordNet;
    @org.junit.Before
    public void setUp() throws Exception {
        wordNet = new WordNet("data\\synsets100-subgraph.txt", "data\\hypernyms100-subgraph.txt");
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
        assertEquals("unit building_block", wordNet.sap("fibrinase", "unit"));
    }

    @org.junit.Test
    public void main() throws Exception {

    }
    
}