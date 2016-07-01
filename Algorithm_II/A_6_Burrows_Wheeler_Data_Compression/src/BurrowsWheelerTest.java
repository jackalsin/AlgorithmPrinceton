import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author jacka
 * @version 1.0 on 6/30/2016.
 */
public class BurrowsWheelerTest {

    private static String DECODED_INPUT = "ABRACADABRA!";

    private static byte[] ENCODED_INPUT = { 0x0, 0x0, 0x0, 0x3,
            0x41, 0x52, 0x44, 0x21, 0x52, 0x43, 0x41, 0x41, 0x41,
            0x41, 0x42, 0x42 };

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void encodeTest() throws Exception {
        System.setIn(new ByteArrayInputStream(DECODED_INPUT.getBytes()));
        // create new output stream as byte array and assign to standard
        ByteArrayOutputStream resultOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultOutputStream));

        BurrowsWheeler.encode();
        byte[] encoded = resultOutputStream.toByteArray();

        System.err.println("output = \t" + Arrays.toString(encoded));
        System.err.println("reference = " + Arrays.toString(ENCODED_INPUT));
        // 4 = 32/ 8 int is 4 bytes
        for (int i = 0; i < 4; i++) {
            assertEquals(ENCODED_INPUT[i], encoded[i]);
        }
        // letters
        for (int i = 4; i < encoded.length; i++) {
            assertEquals(ENCODED_INPUT[i], encoded[i]);
        }

        System.setIn(null);
        System.setOut(null);
    }

    @Test
    public void decodeTest() throws Exception {
        System.setIn(new ByteArrayInputStream(ENCODED_INPUT));
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        BurrowsWheeler.decode();
        String decoded = resultStream.toString();
        // check length and chars
        assertEquals(DECODED_INPUT.length(), decoded.length());
        assertEquals(DECODED_INPUT, decoded);

        System.setIn(null);
        System.setOut(null);
    }
    
}