package assignment_1_percolation.test;

import assignment_1_percolation.main.Percolation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jacka
 * @version 1.0 on 6/8/2016.
 */
public class PercolationTest {

    Percolation percolation;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void percolates1NotOpen() throws Exception {
        percolation = new Percolation(1);
        assertFalse(percolation.percolates());
        assertFalse(percolation.isFull(1,1));
    }

    @Test
    public void percolates1Open() throws Exception {
        percolation = new Percolation(1);
        percolation.open(1,1);
        assertTrue(percolation.percolates());
    }
    @Test
    public void percolates2NotOpen() throws Exception {
        percolation = new Percolation(2);
        assertFalse(percolation.percolates());
    }
    @Test
    public void percolates2Open() throws Exception {
        percolation = new Percolation(2);
        percolation.open(1,1);
        percolation.open(2,1);
        assertTrue(percolation.percolates());
    }


    
}