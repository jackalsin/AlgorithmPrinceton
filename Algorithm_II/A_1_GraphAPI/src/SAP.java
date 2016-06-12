import edu.princeton.cs.algs4.*;

import java.util.*;
import java.util.Queue;

/**
 * @author jacka
 * @version 1.0 on 6/11/2016.
 */
public class SAP {

    private final Digraph digraph;

    public SAP(Digraph G) {
        if (G == null) throw new NullPointerException();
        digraph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v > digraph.V() - 1)
            throw new IndexOutOfBoundsException("v should be in range of [0, digraph.v() - 1), but v = " + v);
        if (w < 0 || w > digraph.V() - 1)
            throw new IndexOutOfBoundsException("w should be in range of [0, digraph.v() - 1), but w = " + w);
//        System.out.println("call ancestor v =" + v + " w = " + w);
        /* traverse all ancestors of v, then traverse ancestors of w, check if visited.*/
        if (v == w) return 0;
        Queue<Integer> path = new ArrayDeque<>();
        path.add(v);
        Map<Integer, Integer> ancestorToDist = new HashMap<>();
        ancestorToDist.put(v, 0);
        bfsTraverseV(digraph, path, ancestorToDist);
        return bfsTraverseW(digraph,w, ancestorToDist);
    }

    private void bfsTraverseV(Digraph G, Queue<Integer> path, Map<Integer, Integer> ancestorToDist) {
        int dist = 1;
        while (!path.isEmpty()) {
            Integer number = path.remove();
            int currentDist = ancestorToDist.get(number);
            Iterable<Integer> itr = G.adj(number);
            for (Integer ancestor : itr) {
                if (!ancestorToDist.keySet().contains(ancestor)) {
                    path.add(ancestor);
                    ancestorToDist.put(ancestor, currentDist + 1);
                } // visited, do nothing
            }
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
        Map<Integer, Integer> wToDist = new HashMap<>();
        wToDist.put(w, 0);
        int distFromW = 0;
        int minDist = Integer.MAX_VALUE;
        if (visited.contains(w))  minDist = ancestorToDist.get(w); // check root
        while(!path.isEmpty()) {
            Integer number = path.remove();
            Iterable<Integer> itr = G.adj(number);
            distFromW = wToDist.get(number) + 1;
            for (Integer ancestor : itr) {
                if (visited.contains(ancestor)) {
                    int distFromV = ancestorToDist.get(ancestor);
                    if (distFromV + distFromW < minDist)
                        minDist = distFromV + distFromW;
                }
                if (!wToDist.keySet().contains(ancestor)) {
                    path.add(ancestor);
                    wToDist.put(ancestor, distFromW);
                }
            }
        }
        if (minDist == Integer.MAX_VALUE) return -1;
        return minDist;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return ancestorHelper(v, w)[0];
    }

    private int[] ancestorHelper(int v, int w) {
        if (v < 0 || v > digraph.V() - 1)
            throw new IndexOutOfBoundsException("v should be in range of [0, digraph.v() - 1), but v = " + v);
        if (w < 0 || w > digraph.V() - 1)
            throw new IndexOutOfBoundsException("w should be in range of [0, digraph.v() - 1), but w = " + w);

        /* traverse all ancestors of v, then traverse ancestors of w, check if visited.*/
        Queue<Integer> path = new ArrayDeque<>();
        path.add(v);
        Map<Integer, Integer> ancestorToDist = new HashMap<>();
        ancestorToDist.put(v, 0);
        bfsTraverseV(digraph, path, ancestorToDist);
        return bfsTraverseWwithAncestor(digraph, w, ancestorToDist);
    }

    /**
     * Traverse G from source point w, return ancestor that can result the min distance to the v's ancestor
     * @param G digraph G
     * @param w source
     * @param ancestorToDist ancestor of v and its ancestor.
     * @return an array
     *          Index 0 : ancestor that can result the min distance to the v's ancestor
     *          Index 1 : distance
     */
    private int[] bfsTraverseWwithAncestor(Digraph G, int w, final Map<Integer, Integer> ancestorToDist) {
        Queue<Integer> path = new ArrayDeque<>();
        path.add(w);
        Set<Integer> visited = ancestorToDist.keySet();
        // isAncestor
        int minDist = Integer.MAX_VALUE;
        int minAncestor = -1;
        if (visited.contains(w)) {
            minDist = ancestorToDist.get(w);
            minAncestor = w;
        }
        int curDist = 0;
        Map<Integer, Integer> wToDist = new HashMap<>();
        wToDist.put(w, 0);
        while (!path.isEmpty()) {
            Integer number = path.remove();
            Iterable<Integer> itr = G.adj(number);
            curDist = wToDist.get(number) + 1;
            for (Integer ancestor : itr) {
                if (visited.contains(ancestor)) {
                    int distFromV = ancestorToDist.get(ancestor);
                    if (distFromV + curDist < minDist) {
                        minDist = distFromV + curDist;
                        minAncestor = ancestor;
                    }
                }
                if (!wToDist.keySet().contains(ancestor)) {
                    path.add(ancestor);
                    wToDist.put(ancestor, curDist);
                }
            }
        }
        if (minDist == Integer.MAX_VALUE) minDist = -1;
        return new int[] {minAncestor, minDist};
    }
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null) throw new NullPointerException("v should not be null ");
        if (w == null) throw new NullPointerException("w should not be null ");
        int minDist = Integer.MAX_VALUE;
        for (Integer vChild: v) {
            for (Integer wChild: w) {
                int curDist = length(vChild, wChild);
                if (curDist >= 0 && curDist < minDist)
                    minDist = curDist;
            }
        }
        if (minDist == Integer.MAX_VALUE) return -1;
        return minDist;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null) throw new NullPointerException("v should not be null ");
        if (w == null) throw new NullPointerException("w should not be null ");

        int minDist = Integer.MAX_VALUE;
        int minAncestor = -1;
        for (Integer vChild: v) {
            for (Integer wChild: w) {
                int[] result = ancestorHelper(vChild, wChild);
                int curAncestor = result[0];
                int curDist = result[1];
                if (curDist >= 0 && curDist < minDist) {
                    minDist = curDist;
                    minAncestor = curAncestor;
                }
            }
        }

        return minAncestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}












