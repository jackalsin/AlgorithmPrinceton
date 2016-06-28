/**
 * @author jacka
 * @version 1.0 on 6/27/2016.
 */
public class TST<Value> {

    private Node root;

    public TST() {

    }


    // ----------- getter ----------------
    public boolean contains(String key) {
        // unnecessary to check the key == null
        return get(key) != null;
    }

    public Value get(String key) {
        if (key == null)
            throw new NullPointerException("key cannot be null, from " + getClass().getName());
        if (key.equals(""))
            throw new IllegalArgumentException("key cannot be an empty string");
        Node x = get(key, root, 0);
        if (x == null)
            return null;
        else
            return x.val;
    }

    private Node get(String key, Node node, int index) {
        if (node == null)
            return null;
        else {
            char tgtChar = key.charAt(index);
            if (tgtChar < node.c)
                return get(key, node.left, index);
            else if (tgtChar > node.c) {
                return get(key, node.right, index);
            } else {
                // hit
                if (index == key.length() - 1)
                    return node;
                else
                    return get(key, node.mid, index + 1);
            }
        }
    }


    // ----------------- setter -------------
    public void put(String key, Value val) {
        if (key == null)
            throw new NullPointerException("key cannot be null");
        else if (key.length() == 0)
            throw new IllegalArgumentException("key length must be greater or equals to 1");
        else
            root = put(root, key, val, 0);
    }

    private Node put(Node node, String key, Value val, int d) {
        if (node == null) {
            node = new Node();
            node.c = key.charAt(d);
        }
        char c = key.charAt(d);
        char midChar = node.c;
        if (c < midChar) node.left = put(node.left, key, val, d);
        else if (c > midChar) node.right = put(node.right, key, val, d);
        else if (c == midChar && d < key.length() - 1) node.mid = put(node.mid, key, val, d + 1);
        else node.val = val;
        return node;
    }

    // ------------------ prefix operation --------------
    public boolean hasPrefix(String prefix) {
        Node prefixNode = get(prefix, root, 0);
        return prefixNode != null && (prefixNode.val != null || !(prefixNode.left == null && prefixNode.mid == null && prefixNode.right == null));

    }


    // ---------------------- Node class ----------------
    /* inner class of node */
    private class Node {
        private char c;
        private Node left, mid, right;
        private Value val;

    }

}
