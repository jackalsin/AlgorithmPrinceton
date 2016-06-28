import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author jacka
 * @version 1.0 on 6/28/2016.
 */
public class TestTST {

    private static final String DICT = "test/dictionary-algs4.txt";

    private TST<Integer> tst;
    @Before
    public void setUp() {
        tst = new TST<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(DICT))) {
            String line;
            while ((line = bf.readLine()) != null) {
                tst.put(line, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test hasPrefix method with abacus
     */
    @Test
    public void testHasPrefixAbacus() {
        assertTrue(tst.hasPrefix("A"));
        assertTrue(tst.hasPrefix("AB"));
        assertTrue(tst.hasPrefix("ABA"));
        assertTrue(tst.hasPrefix("ABAC"));
        assertTrue(tst.hasPrefix("ABACU"));
        assertTrue(tst.hasPrefix("ABACUS"));
    }

    @Test
    public void testHasPrefixFalse() {
        assertFalse(tst.hasPrefix("AA"));
    }

    @Test
    public void testContainsSense() {
        assertTrue(tst.contains("SENSE"));
    }

    @Test
    public void testGetSense() {
        assertEquals(new Integer(1), tst.get("SENSE"));
    }
}
