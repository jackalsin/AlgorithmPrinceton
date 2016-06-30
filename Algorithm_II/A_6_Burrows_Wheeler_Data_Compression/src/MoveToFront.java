import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jacka
 * @version 1.0 on 6/29/2016.
 */
public class MoveToFront {
    private static final int R = 256;

    /**
     * Initialize the sequence by making the ith character in the sequence equal to the ith
     * extended ASCII character. Now, read in each 8-bit character c from standard input one
     * at a time, output the 8-bit index in the sequence where c appears, and move c to the front.
     */
    public static void encode() {
        char[] sequence = initializeCharArray();

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();

            for (int i = 0; i < R; i++) {
                if (c == sequence[i]) {
                    System.arraycopy(sequence, 0, sequence, 1, i);
                    sequence[0] = c;
                    BinaryStdOut.write(i, 8);
                    break;
                }
            }

        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] sequence = initializeCharArray();

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();

            /*
             * ABRACADABRA!
             * read in each 8-bit character i (but treat it
             * as an integer between 0 and 255) from standard input one
             * at a time, write the ith character in the sequence, and
             * move that character to the front.  */
            char newHead = sequence[c];
            BinaryStdOut.write(newHead, 8);
            System.arraycopy(sequence, 0, sequence, 1, c);
            sequence[0] = newHead;


        }
        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    private static char[] initializeCharArray() {
        // unnecessary to change it Character list, since the remove still takes O(n);
        char[] sequence = new char[R];
        for (int i = 0; i < R; i++) {
            sequence[i] = (char) i;
        }
        return sequence;
    }


    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args == null)
            throw new NullPointerException("args cannot be null");
        if (args.length == 0)
            throw new IllegalArgumentException("args cannot be an empty string");
        switch (args[0]) {
            case "-":
                encode();
                break;
            case "+":
                decode();
                break;
            default:
                break;
        }
    }
}
