package _001_unionFind;

/**
 * @author jacka
 * @version 1.0 on 7/2/2017.
 */
public final class QuickFind implements UnionFind{
  private final int[] id;

  public QuickFind(final int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  @Override
  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }

  @Override
  public void union(int p, int q) {
    int pid = id[p];
    int qid = id[q];

    for (int i = 0; i < id.length; i++) {
      if (id[i] == qid) {
        id[i] = id[p];
      }
    }
  }
}
