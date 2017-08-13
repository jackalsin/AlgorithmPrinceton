package _001_Knuth_Morris_Pratt;

/**
 * @author jacka
 * @version 1.0 on 8/12/2017.
 */
public final class KMP {
  private static final int R = 256;
  private final int[][] dfa;
  private static final int NOT_FOUND = -1;

  public KMP(String pattern) {
    dfa = new int[R][pattern.length()];
    construct(pattern);
  }

  public void construct(String pattern) {
    final int M = pattern.length();
    dfa[pattern.charAt(0)][0] = 1;
    for(int X = 0, j = 1; j < M; j++) {

      for (int c = 0; c < R; c++) {
        dfa[c][j] = dfa[c][X];
      }
      dfa[pattern.charAt(j)][j] = j + 1;
      X = dfa[pattern.charAt(j)][X];
    }

  }

  public int search(String targetString) {
    final int N = targetString.length();
    final int M = dfa[0].length;
    int i = 0, j = 0;
    for (; i < N && j < M; ++i) {
      j = dfa[targetString.charAt(i)][j];
    }
    if (j == M) {
      return i - M;
    } else {
      return NOT_FOUND;
    }
  }
}
