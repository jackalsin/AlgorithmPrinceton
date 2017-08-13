package _001_Knuth_Morris_Pratt;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * @author jacka
 * @version 1.0 on 8/12/2017.
 */
public class KMPTest {
  private KMP kmp;

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testABABAC() throws Exception {
    final String pattern = "ABABAC";
    kmp = new KMP(pattern);
    Field f = kmp.getClass().getDeclaredField("dfa");
    f.setAccessible(true);
    int[][] dfa = (int[][]) f.get(kmp);
    f.setAccessible(false);
    final int[][] expected = new int[][] {
        {1, 1, 3, 1, 5, 1},
        {0, 2, 0, 4, 0, 4},
        {0, 0, 0, 0, 0, 6}
    };
    final int[][] actual = new int[3][dfa[0].length];
    for (int i = 'A'; i < 'D'; i++) {
      System.arraycopy(dfa[i], 0, actual[i - 'A'], 0, dfa[0].length);
    }
    assertTrue(twoDimensionArrayEquals(expected, actual));
  }

  private static boolean twoDimensionArrayEquals(int[][] expected, int[][] actual) {
    if (actual.length == expected.length && actual[0].length == expected[0].length) {
      for (int row = 0; row < expected.length; row++) {
        for (int col = 0; col < expected[0].length; col++) {
          if (expected[row][col] != actual[row][col]) {
            return false;
          }
        }
      }
      return true;
    }
    return false;
  }

  @Test
  public void testSearch() throws Exception {
    final String pattern = "ABABAC";
    kmp = new KMP(pattern);
    assertEquals(0, kmp.search("ABABAC"));
    assertEquals(3, kmp.search("AAAABABAC"));
  }
}
