package test;

import main.WeightedQuickUnion;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author jacka
 * @version 1.0 on 5/10/2016
 *          <p>
 *          Created by jacka on 5/10/2016.
 */
public class WeightedTestQuickUnion {

    private WeightedQuickUnion weightedQuickUnion;

    @Before
    public void setUp() {
        weightedQuickUnion = new WeightedQuickUnion("conn1.txt");
    }

    @Test
    public void testDirect() {
        assertTrue(weightedQuickUnion.isConnected(6, 0));
    }

    @Test
    public void test98() {
        assertTrue(weightedQuickUnion.isConnected(9, 8));
    }

}
