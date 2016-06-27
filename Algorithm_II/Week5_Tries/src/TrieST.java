/**
 * @author jacka
 * @version 1.0 on 6/22/2016.
 */
public class TrieST<Value> {
    /* Extended ASCII */
    private static final int R = 256;
    private Node root = new Node();
    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is <tt>null</tt>, this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) { x.val = val; return x; }
        char c = key.charAt(d);
        /* When creating a new node, the x.next[c] will be null, so it will be created
        * in the next recursion */
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }
}
