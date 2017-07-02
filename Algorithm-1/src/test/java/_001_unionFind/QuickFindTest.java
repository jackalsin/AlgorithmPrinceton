package _001_unionFind;

import org.junit.Before;
import org.junit.Test;

import java.lang.ref.SoftReference;

import static org.junit.Assert.*;

/**
 * @author jacka
 * @version 1.0 on 7/2/2017.
 */
public final class QuickFindTest {
  private QuickFind quickFind;
  @Before
  public void setUp() throws Exception {
    quickFind = new QuickFind(10);
  }

  @Test
  public void testUnion() throws Exception {
    quickFind.union(1, 2);
    quickFind.union(1, 9);
    quickFind.union(0, 5);
    quickFind.union(5, 6);
    quickFind.union(3, 4);
    quickFind.union(9, 7);
    quickFind.union(7, 8);
    quickFind.union(8, 2);

    assertTrue(quickFind.connected(2, 7));
  }

}