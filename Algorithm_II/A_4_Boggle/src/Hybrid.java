import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jacka
 * @version 1.0 on 6/28/2016.
 */
public class Hybrid<Value> {

    private static final int R = 26; //26 * 26;

    private final TST<Value>[][] roots;

    private final List<List<Value>> rootScore; // = new ArrayList<>();

    private final List<Value> singleRootScore; // = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public Hybrid() {
        roots = new TST[R][R];
        singleRootScore = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            singleRootScore.add(null);
        }
        rootScore = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            List<Value> toAdd = new ArrayList<>();
            for (int j = 0; j < R; j++) {
                toAdd.add(null);
            }
            rootScore.add(toAdd);
        }
    }

    public void put(String key, Value val) {
        if (key == null)
            throw new NullPointerException("key cannot be null");
        else if (key.length() == 0)
            throw new IllegalArgumentException("key length must be greater or equals to 1");
        else if (key.length() == 1) {
            singleRootScore.set(key.charAt(0) - 'A', val);
        } else if (key.length() == 2) {
            List<Value> toChange = rootScore.get(key.charAt(0) - 'A');
            toChange.set(key.charAt(1) - 'A', val);
            rootScore.set(key.charAt(0) - 'A', toChange);
        } else {
            TST<Value> tgtCell = getTST(key);
            if (tgtCell == null)
                tgtCell = new TST<>();

            tgtCell.put(key.substring(2), val);
            setTST(key, tgtCell);
        }
    }

    public boolean hasPrefix(String initStr) {
        if (initStr == null)
            throw new NullPointerException("key cannot be null");
        if (initStr.length() == 0) {
            // iterate all cell
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < R; j++) {
                    if (roots[i][j] != null)
                        return true;
                }
            }
            return false;
        } else if (initStr.length() == 1) {
            TST<Value>[] tsts = roots[initStr.charAt(0) - 'A'];
            for (int i = 0; i < R; i++) {
                if (tsts[i] != null)
                    return true;
            }
            return false;
        } else if (initStr.length() == 2) {
            TST<Value> tgtCell = getTST(initStr);
            return (tgtCell != null);
        } else {
            // init.length >= 3
            TST<Value> tgtCell = getTST(initStr);
            if (tgtCell == null)
                return false;
            else
                return tgtCell.hasPrefix(initStr.substring(2));
        }
    }

    public boolean contains(String str) {

        return get(str) != null;
    }

    public Value get(String key) {
        if (key == null)
            throw new NullPointerException("key cannot be null");
        else if (key.length() == 0)
            throw new IllegalArgumentException("the length of key cannot be 0");
        else if (key.length() == 1) {
            return singleRootScore.get(key.charAt(0) - 'A');
        } else if (key.length() == 2) {
            return rootScore.get(key.charAt(0) - 'A').get(key.charAt(1) - 'A');
        } else {
            TST<Value> tgtCell = getTST(key);
            if (tgtCell == null)
                return null;
            else
                return tgtCell.get(key.substring(2));
        }

    }


    private TST<Value> getTST(String key) {
        if (key == null)
            throw new NullPointerException();
        if (key.length() < 2)
            throw new IllegalArgumentException("key length = " + key.length());
        int row = key.charAt(0) - 'A';
        int col = key.charAt(1) - 'A';

        return roots[row][col];
    }

    private void setTST(String key, TST<Value> tst) {
        if (key == null)
            throw new NullPointerException();
        if (key.length() < 3)
            throw new IllegalArgumentException("key length = " + key.length());
        int row = key.charAt(0) - 'A';
        int col = key.charAt(1) - 'A';
        roots[row][col] = tst;
    }
}
