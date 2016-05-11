package test;

import main.QuickFind;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test class of quick find.
 *
 * @author jacka
 * @version 1.0.0 on 5/6/2016.
 */
public class testQuickFind {

    private QuickFind quickFind;
    @Before
    public void setUp() {
        quickFind = new QuickFind("conn1.txt");
    }

    @Test
    public void testDirect() {
        assertTrue(quickFind.isConnected(6,0));
    }

    @Test
    public void test98() {
        assertTrue(quickFind.isConnected(9,8));
    }
}
