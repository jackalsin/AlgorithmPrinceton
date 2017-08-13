package _002_boyer_moore;

import java.util.Arrays;

/**
 * @author jacka
 * @version 1.0 on 8/13/2017.
 */
public final class BM {
  private static final int R = 256;
  private final int[] right;
  private final String patten;
  private final int M;
  private static final int NOT_FOUND = -1;
  public BM(String pattern) {
    right = new int[R];
    Arrays.fill(right, -1);
    this.patten = pattern;
    M = pattern.length();
    for (int i = 0; i < M; ++i) {
      right[pattern.charAt(i)] = i;
    }
  }

  public int search(String s) {
    final int N = s.length();
    int skip = 0;
    for (int i = 0; i <= N - M; i += skip) {
      skip = 0;
      for (int j = M - 1; j >= 0; j--) {
        if (patten.charAt(j) != s.charAt(i + j)) {
          skip = Math.max(1, j - right[s.charAt(i + j)]);
          break;
        }
      }
      if (skip == 0) return i;
    }
    return NOT_FOUND;
  }

  public static void main(String[] args ) {
    final String input = "ABCDABD";
    final String pattern = "ABD";

    BM bm = new BM(pattern);
    int startIndex = bm.search(input);
    System.out.println(startIndex + " should equal to 4");

    BM needle = new BM("NEEDLE");
    startIndex = needle.search("NEEEEELEEEEEEEDNEEDLE");
    System.out.println(startIndex + " should equal to 15");
  }
}
