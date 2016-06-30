import java.util.Arrays;

/**
 * Given a text file in which sequences of the same character occur near each other many times, convert it into a text
 * file in which certain characters appear more frequently than others.
 *
 * @author jacka
 * @version 1.0 on 6/29/2016.
 */
public class CircularSuffixArray {
    /* the raw String */
    private String rawString;
    private Integer[] index;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new NullPointerException("s cannot be null");
        if (s.isEmpty())
            throw new IllegalArgumentException("s cannot be empty");
        this.rawString = s;
        index = new Integer[rawString.length()];
        for (int i = 0; i < rawString.length(); i++)
            index[i] = i;
        int strLen = rawString.length();

        /* The basic idea in this function is to compare one char by one char
        * if rawString is 'ABCDEF' and index1 = 0, index2 = 4, then
        * Comparing 'ABCDEF' and 'EFABCD' */
        Arrays.sort(index, (index1, index2) -> {
            for (int i = 0; i < strLen; i++) {
                if (index1.compareTo(strLen) >= 0) {
                    index1 = 0;
                }
                if (index2.compareTo(strLen) >= 0) {
                    index2 = 0;
                }
                char char1 = rawString.charAt(index1);
                char char2 = rawString.charAt(index2);
                if (char1 == char2) {
                    index1++;
                    index2++;
                } else {
                    return char1 - char2;
                }
            } // end of for loop
            return 0;
        });
    }

    // length of s
    public int length() {
        return rawString.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        return index[i];
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");

    }
}
