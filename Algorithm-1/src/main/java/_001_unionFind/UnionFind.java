package _001_unionFind;

/**
 * @author jacka
 * @version 1.0 on 7/2/2017.
 */
public interface UnionFind {

  boolean connected(int p, int q);

  void union(int p, int q);
}
