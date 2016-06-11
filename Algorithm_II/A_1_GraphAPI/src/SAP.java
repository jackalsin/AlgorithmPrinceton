import edu.princeton.cs.algs4.*;

import java.util.*;
import java.util.Queue;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public final class SAP {

    private final Digraph digraph;

    public SAP(Digraph G) {
        if (G == null) throw new NullPointerException();
        digraph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v <= 0 || v > digraph.V() - 1)
            throw new IndexOutOfBoundsException("v should be in range of [0, digraph.v() - 1), but v = " + v);
        if (w <= 0 || w > digraph.V() - 1)
            throw new IndexOutOfBoundsException("w should be in range of [0, digraph.v() - 1), but w = " + v);

//        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
//        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);
        /* traverse all ancestors of v, then traverse ancestors of w, check if visited.*/
        Queue<Integer> path = new ArrayDeque<>();
        path.add(v);
        Map<Integer, Integer> ancestorToDist = new HashMap<>();
        ancestorToDist.put(v, 0);
        bfsTraverseV(digraph, path, ancestorToDist);
        return bfsTraverseW(digraph,w,ancestorToDist);
    }

    private void bfsTraverseV(Digraph G, Queue<Integer> path, Map<Integer, Integer> ancestorToDist) {
        int dist = 1;
        while(!path.isEmpty()) {
            Integer number = path.remove();
            Iterable<Integer> itr = G.adj(number);
            for (Integer ancestor : itr) {
                if (!ancestorToDist.keySet().contains(ancestor)) {
                    path.add(ancestor);
                    ancestorToDist.put(ancestor,dist);
                } // visited, do nothing
            }
            dist ++;
        }

    }

    /**
     * Traverse G from source point w, return the min distance to the v's ancestor
     * @param G diagraph G
     * @param w source
     * @param ancestorToDist ancestor of v and its ancestor.
     * @return return the min distance to the v's ancestor
     */
    private int bfsTraverseW(Digraph G, int w, final Map<Integer, Integer> ancestorToDist) {
        Queue<Integer> path = new ArrayDeque<>();
        path.add(w);
        Set<Integer> visited = ancestorToDist.keySet();
        int curDist = 0;
        int minDist = Integer.MAX_VALUE;
        while(!path.isEmpty()) {
            Integer number = path.remove();
            Iterable<Integer> itr = G.adj(number);
            curDist++;
            for (Integer ancestor : itr) {
                if (visited.contains(ancestor)) {
                    int distFromV = ancestorToDist.get(ancestor);
                    if (distFromV + curDist < minDist)
                        minDist = distFromV + curDist;
                } else {
                    path.add(ancestor);
                }
            }
        }
        if (minDist == Integer.MAX_VALUE) return -1;
        return minDist;
    }
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return 0;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
