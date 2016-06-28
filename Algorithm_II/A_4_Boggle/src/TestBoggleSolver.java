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

    private static final String DICT = "test/dictionary-algs4.txt";
    private static final String BOARD = "test/board4x4.txt";

    private BoggleSolver solver;
    private int score;

    @Before
    public void setUp() {
        In in = new In(DICT);
        String[] dictionary = in.readAllStrings();
        solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(BOARD);
        Iterable<String> allWords = solver.getAllValidWords(board);
        for (String word : allWords) {
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
