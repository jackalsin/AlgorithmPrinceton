import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

/**
 * @author jacka
 * @version 1.0 on 6/28/2016.
 */
public class HybridTest {

    private static final String DICT = "test/dictionary-algs4.txt";

    private Hybrid<Integer> hybrid;
    @Before
    public void setUp() {
        hybrid = new Hybrid<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(DICT))) {
            String line;
            while ((line = bf.readLine()) != null) {
                hybrid.put(line, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("initialization finishes ");
    }

    /**
     * Test hasPrefix method with abacus
     */
    @Test
    public void testHasPrefixAbacus() {
        assertTrue(hybrid.hasPrefix("A"));
        assertTrue(hybrid.hasPrefix("AB"));
        assertTrue(hybrid.hasPrefix("ABA"));
        assertTrue(hybrid.hasPrefix("ABAC"));
        assertTrue(hybrid.hasPrefix("ABACU"));
        assertTrue(hybrid.hasPrefix("ABACUS"));
    }

    @Test
    public void testHasPrefixFalse() {
        assertFalse(hybrid.hasPrefix("AA"));
    }

    @Test
    public void testContainsSense() {
        assertTrue(hybrid.contains("SENSE"));
    }

    @Test
    public void testGetSense() {
        assertEquals(new Integer(1), hybrid.get("SENSE"));
    }
    
}