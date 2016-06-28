import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jacka
 * @version 1.0 on 6/28/2016.
 */
public class TestBoggleSolver {

    private BoggleSolver boggleSolver;

    private static final String DICT = "test/dictionary-algs4.txt";
    private static final String BOARD = "test/board4x4.txt";

    private int score;

    @Before
    public void setUp() {
        In in = new In(DICT);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(BOARD);
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }

    @Test
    public void testScoreOf() {
        assertEquals(33, score);
    }


}
