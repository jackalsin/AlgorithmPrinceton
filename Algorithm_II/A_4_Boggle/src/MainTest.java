import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.TrieST;

/**
 * @author jacka
 * @version 1.0 on 6/28/2016.
 */
public class MainTest {

    public static void main(String[] args) {
        In in = new In("test/dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        TrieST<Integer> integerTrieST = new TrieST<>();
        for (String word : dictionary) {
            integerTrieST.put(word, 1);
        }

        System.out.println(integerTrieST.contains("ABACUS"));
        System.out.println(integerTrieST.longestPrefixOf("ABA"));
    }
}
