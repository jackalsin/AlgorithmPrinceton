import java.util.Arrays;

/**
 * Given a text file in which sequences of the same character occur near each other many times, convert it into a text
 * file in which certain characters appear more frequently than others.
 *
 * quick sort reference http://algs4.cs.princeton.edu/51radix/Quick3string.java.html
 * @author jacka
 * @version 1.0 on 6/29/2016.
 */
public class CircularSuffixArray {
    /* the raw String */
    private String rawString;
    private Integer[] index;

    private final int CUTOFF;   // cutoff to insertion sort


    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new NullPointerException("s cannot be null");
        this.rawString = s;
        CUTOFF = 45;
        index = new Integer[rawString.length()];
        for (int i = 0; i < rawString.length(); i++)
            index[i] = i;

        sort(s, 0, s.length() - 1, 0);
    }

    // ------ 3 way quick sort --------------------
    private void sort(String s, int lo, int hi, int offset) {
        if (hi <= lo + CUTOFF) {
            insertion(s, lo, hi, offset);
            return;
        }
        int lt = lo, gt = hi, v = charAt(s, index[lo], offset), i = lo + 1;
        while (i <= gt) {
            int t = charAt(s, index[i], offset);
            if      (t < v) exch(lt++, i++);
            else if (t > v) exch(i, gt--);
            else              i++;
        }
        sort(s, lo, lt - 1, offset);
        if (v >= 0) sort(s, lt, gt, offset + 1);
        sort(s, gt + 1, hi, offset);
    }

    private char charAt(String s, int suffix, int offset) {
        return s.charAt((suffix + offset) % s.length());
    }

    private void exch(int i, int j) {
        int temp = index[i];
        index[i] = index[j];
        index[j] = temp;
    }

    private void insertion(String s, int lo, int hi, int offset) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(s, j, j - 1, offset); j--)
                exch(j, j - 1);
    }

    private boolean less(String s, int i, int j, int offset) {
        int oi = index[i], oj = index[j];
        for (; offset < index.length; offset++) {
            int ival = charAt(s, oi, offset), jval = charAt(s, oj, offset);
            if (ival < jval)
                return true;
            else if (ival > jval)
                return false;
        }
        return false;
    }
    // ------------------------- end of sort ----------------------

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
