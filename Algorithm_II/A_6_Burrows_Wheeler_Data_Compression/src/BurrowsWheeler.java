import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * This is implementing BurrowsWheeler Algorithm
 *
 * @author jacka
 * @version 1.0 on 6/29/2016.
 */
public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        /* java BurrowsWheeler - < abra.txt */
        /* A    B   R   A   C   A   D   A   B   R   A   !
        *   */
        String inputStr = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(inputStr);
        // get First
        int first = -1;
        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                first = i;
                BinaryStdOut.write(i);
                break;
            }
        } // end of for loop

        int csaLen = csa.length();
        char[] result = new char[csaLen];

        for (int i = 0; i < csaLen; i++) {
            int index = csa.index(i);
            if (csa.index(i) == 0)
                result[i] = inputStr.charAt(csaLen - 1);
            else {
                result[i] = inputStr.charAt(index - 1);
            }
        }
        for (int i = 0; i < result.length; i++) {
            BinaryStdOut.write(result[i], 8);
        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
        int first = BinaryStdIn.readInt();
        String input = BinaryStdIn.readString();
        int strLen = input.length();
        char[] t = input.toCharArray();
        int[] next = new int[t.length];

        char[] sorted = input.toCharArray();
        Arrays.sort(sorted);

        int index = first;
        for (int i = 0; i < strLen; i++) {
            char firstCol = sorted[index];
            int preNum = prevCount(sorted, index); // get how many the same char
            next[index] = iThChar(t, preNum, firstCol);
            index = next[index];
        }
//        System.err.println("next = " + Arrays.toString(next));
//        System.err.println("first = " + first); // 3
        // WTF BinaryStdOut!

        for (int i = 0, row = next[first]; i < next.length; i++, row = next[row]) {
//            System.err.println("row = " + row + " t[row] = " + t[row]);
            BinaryStdOut.write(t[row]);
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    private static int iThChar(final char[] t, int preNum, char tgtChar) {
        int count = 0;
        int needNum = preNum + 1;
        for (int i = 0; i < t.length; i++) {
            if (tgtChar == t[i]) {
                count++;
                if (count == needNum)
                    return i;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * To return the number of the same char (sorted[int]) has appeared in a sorted array.
     * @param sorted a sorted char array
     * @param index index of the tgt char
     * @return the repeated times
     */
    private static int prevCount(final char[] sorted, int index) {
        char tgt = sorted[index];
        int prevCount = 0;
        int pt = index - 1;
        while (pt >= 0 && sorted[pt--] == tgt)
            prevCount++;
        return prevCount;
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if (args == null)
            throw new NullPointerException("args cannot be null");
        if (args.length == 0)
            throw new IllegalArgumentException("args cannot be empty");

        switch (args[0]) {
            case "+": decode(); break;
            case "-": encode(); break;
            default:
                throw new IllegalArgumentException("args[0] can only be '+' or '-'" );
        }
    }
}
