import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class OutcastTest {
    private static final String outcast5 = "data\\outcast5.txt";
    private static final String outcast8 = "data\\outcast8.txt";
    private static final String outcast11 = "data\\outcast11.txt";
    private static final String synset = "data\\digraph1.txt";
    private static final String hypernyms = "data\\digraph2.txt";

    private Outcast outcast;
    @Before
    public void setUp() throws IOException {
        WordNet wordNet = new WordNet(synset, hypernyms);
        outcast = new Outcast(wordNet);

    }

    @Test
    public void testOutcast5() {
        In in = new In(outcast5);
        String[] nouns = in.readAllStrings();
        assertEquals("table", outcast.outcast(nouns));
    }

    @Test
    public void testOutcast8() {
        In in = new In(outcast8);
        String[] nouns = in.readAllStrings();
        assertEquals("bed", outcast.outcast(nouns));
    }


    @Test
    public void testOutcast11() {
        In in = new In(outcast11);
        String[] nouns = in.readAllStrings();
        assertEquals("potato", outcast.outcast(nouns));
    }

    public static void main() {
        Outcast.main(new String[] {synset,
                hypernyms, outcast5, outcast8, outcast11});
    }
}