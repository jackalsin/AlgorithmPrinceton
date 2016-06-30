import org.junit.After;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Test class for MoveToFront Class
 *
 * @author jacka
 * @version 1.0 on 6/29/2016.
 */
public class MoveToFrontTest {
    /* From abra.txt */
    private static String DECODED_INPUT = "ABRACADABRA!";
    /* From abra.txt */
    private static byte[] ENCODED_INPUT = {0x41, 0x42, 0x52,
            0x2, 0x44, 0x1, 0x45, 0x1, 0x4, 0x4, 0x2, 0x26};

    private ByteArrayOutputStream resultOutStream;


    @org.junit.Before
    public void setUp() throws Exception {

        // create new output stream as byte array and assign to standard
        resultOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultOutStream));
    }

    @org.junit.Test
    public void encodeTest() throws Exception {
        System.setIn(new ByteArrayInputStream(DECODED_INPUT.getBytes()));
        MoveToFront.encode();
        byte[] encoded = resultOutStream.toByteArray();
        System.err.println("printing encoded = " + Arrays.toString(encoded));
        System.err.println("printing encoded = " + Arrays.toString(ENCODED_INPUT));
        for (int i = 0; i < ENCODED_INPUT.length; i++) {
            assertEquals(ENCODED_INPUT[i], encoded[i]);
        }

    }

    @org.junit.Test
    public void decodeTest() throws Exception {
        System.setIn(new ByteArrayInputStream(ENCODED_INPUT));

        MoveToFront.decode();
        String decoded = resultOutStream.toString();

        System.err.println("printing decoded = " + decoded);
        System.err.println("printing decoded = " + DECODED_INPUT);

        for (int i = 0; i < DECODED_INPUT.length(); i++) {
            assertEquals(DECODED_INPUT.charAt(i), decoded.charAt(i));
        }
    }

    @After
    public void toFinish() {
        System.setIn(null);
        System.setOut(null);
    }
}