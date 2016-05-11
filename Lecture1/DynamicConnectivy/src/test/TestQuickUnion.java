package test;

import main.QuickUnion;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author jacka
 * @version 1.0 on 5/10/2016
 *          <p>
 *          Created by jacka on 5/10/2016.
 */
public class TestQuickUnion {

    private QuickUnion quickUnion;

    @Before
    public void setUp() {
        quickUnion = new QuickUnion("conn1.txt");
    }

    @Test
    public void testDirect() {
        assertTrue(quickUnion.isConnected(6,0));
    }

    @Test
    public void test98() {
        assertTrue(quickUnion.isConnected(9,8));
    }

}
