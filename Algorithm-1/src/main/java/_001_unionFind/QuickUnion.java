package _001_unionFind;

/**
 * The problem is that root() can be O(n) when tree is high.
 *
 * @author jacka
 * @version 1.0 on 7/2/2017.
 */
public final class QuickUnion implements UnionFind {
  private final int[] id;

  public QuickUnion(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  private int root(int i) {
//    return id[i] == i ? i: root(id[i]);
    while (i != id[i]) i = id[i];
    return i;
  }

  @Override
  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  @Override
  public void union(int p, int q) {
    id[p] = root(q);
    int rootP = root(p);
    int rootQ = root(q);
    id[rootP] = rootQ;
  }
}
