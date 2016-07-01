import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

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
        char[] chars = input.toCharArray();
        int[] next = new int[chars.length];
        


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
