import com.sun.org.apache.bcel.internal.generic.DLOAD;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieST;

import java.util.HashSet;
import java.util.Set;

/**
 * This is the main implementation of the boggle solver.
 * @author jacka
 * @version 1.0 on 6/27/2016.
 */
public class BoggleSolver {

    private static String INDENT = "";

    private static final int[][] DELTA = new int[][] {
        {-1, 1}, {-1, 0},   {-1, -1},
        {0, 1},             {0, -1},
        {1, 1}, {1, 0},     {1, -1}
    };

    private final TrieST<Integer> dictionary;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null) throw new NullPointerException("dictionary cannot be null");
        this.dictionary = new TrieST<>();
        for (String key : dictionary) {
            this.dictionary.put(key, scoreOf(key));
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null) throw new NullPointerException("board cannot be null");
        Set<String> result = new HashSet<>();
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                String initStr = concat("", board.getLetter(row, col));
                System.out.println("Start Point = (" + row + ", " + col + ")");
                boolean[][] marked = new boolean[board.rows()][board.cols()];
                marked[row][col] = true;
                dfs(result, board, marked, row, col, initStr);
            }
        }

        return result;
    }

    private void dfs(Set<String> result, BoggleBoard board, boolean[][] marked, int startRow, int startCol, String initStr) {
        INDENT += "\t";

        if (isValid(initStr)) result.add(initStr);

        for (int i = 0; i < DELTA.length; i++) {
            System.out.println(" i = " + i);
            int[] array = DELTA[i];
            int curRow = startRow + array[0];
            int curCol = startCol + array[1];
            //TODO: Implement another data structure
            if (curRow < 0 || curRow >= board.rows() || curCol < 0 || curCol >= board.cols())
                continue; // out of boundary
            if (dictionary.longestPrefixOf(initStr) == null) continue;
            marked[curRow][curCol] = true;
            System.out.println(INDENT + "CurRow = " + curRow + " curCol " + curCol + " string = " + initStr);
            dfs(result, board, marked, curRow, curCol, concat(initStr, board.getLetter(curRow, curCol)));
            marked[curRow][curCol] = false;

            INDENT.substring(0, INDENT.length() - 1);
        }
    }

    private boolean isValid(String str) {
        /*
            A valid word can use each die at most once.
            A valid word must contain at least 3 letters.
            A valid word must be in the dictionary (which typically does not contain proper nouns). */
        return !(str == null || str.length() < 3 || !dictionary.contains(str));

    }

    private String concat(String prev, char app) {
        if (app == 'Q') return prev + "QU";
        else return prev + app;
    }


    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        /*
        *   0–2	0
        *   3–4	1
        *   5	2
        *   6	3
        *   7	5
        *   8+	11
        */
        int score;
        int wordLen = word.length();
        switch (wordLen) {
            case 0: case 1: case 2: score = 0; break;
            case 3: case 4: score = 1; break;
            case 5: score = 2; break;
            case 6: score = 3; break;
            case 7: score = 5; break;
            default: score = 11;
        }
        return score;
    }




























    // -------------------------- should be ignored -----------------------
    /**
     * The main function to temporarily run.
     * @param args [dictionary file name] [boggle board file name].
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }

}
