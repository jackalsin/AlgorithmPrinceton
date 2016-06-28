import java.util.Objects;

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
        Node x = getHelper(key, root, 0);
        if (x == null)
            return null;
        else
            return x.val;
    }

    private Node getHelper(String key, Node node, int index) {
        if (node == null)
            return null;
        else {
            char tgtChar = key.charAt(index);
            if (tgtChar < node.c)
                return getHelper(key, node.left, index);
            else if (tgtChar > node.c) {
                return getHelper(key, node.right, index);
            } else {
                // hit
                if (index == key.length() - 1)
                    return node;
                else
                    return getHelper(key, node.mid, index + 1);
            }
        }
    }


    // ----------------- setter



    // ---------------------- Node class ----------------
    /* inner class of node */
    private class Node {
        private char c;
        private Node left, mid, right;
        private Value val;

    }

}
