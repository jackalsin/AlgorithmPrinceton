package _001_unionFind;

import java.util.logging.SocketHandler;

/**
 * When union, the smaller tree goes lower.
 *
 * @author jacka
 * @version 1.0 on 7/2/2017.
 */
public final class WeightedQuickUnion implements UnionFind {

  private final int[] id;
  private final int[] size;

  public WeightedQuickUnion(int N) {
    id = new int[N];
    size = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  private int root(int i) {
    while (id[i] != i) {
      id[i] = id[id[i]];
      i = id[i];
    }
    return i;
  }

  @Override
  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  @Override
  public void union(int p, int q) {
    int rootP = root(p);
    int rootQ = root(q);
    if (rootP != rootQ) {
      int sizeP = size[rootP];
      int sizeQ = size[rootQ];
      if (sizeP < sizeQ) {
        id[rootP] = rootQ;
        size[rootP] += size[rootQ];
      } else {
        id[rootQ] = rootP;
        size[rootQ] += size[rootP];
      }
    }
  }
}
