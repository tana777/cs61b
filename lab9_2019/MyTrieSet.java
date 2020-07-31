import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Lab 9: Tries
 *
 * This lab is from spring 2019 cs61b.
 *
 * @author Tana Gegen 7/30/20
 */

public class MyTrieSet implements TrieSet61B {

    private static final int R = 128; // ASCII
    private Node root; // root of trie
    private static class Node {
        private boolean isKey;
        private DataIndexedCharMap next;

        private Node(boolean b, int R) {
            isKey = b;
            next = new DataIndexedCharMap<Node>(R);
        }

        public class DataIndexedCharMap<V> {
            private V[] items;
            public DataIndexedCharMap(int R) {
                items = (V[]) new Object[R];
            }
        }
    }

    public MyTrieSet() {
        root = new MyTrieSet.Node(false, R);
    }

    @Override
    public void clear() {
        root = new Node(false, R);
    }

    @Override
    public boolean contains(String key) {
        Node ptr = root;
        for (int i = 0; i < key.length(); i++) {
            int num = key.charAt(i);
            if (ptr.next.items[num] != null) {
                ptr = (Node) ptr.next.items[num];
            } else {
                return false;
            }
        }
        if (ptr.isKey) {
            return true;
        }
        return false;
    }

    @Override
    public void add(String key) {
        Node ptr = root;
        for (int i = 0; i< key.length(); i++) {
            int num = key.charAt(i);
            if (ptr.next.items[num] == null) {
                if (i == key.length() - 1) {
                    ptr.next.items[num] = new Node(true, R);
                } else {
                    ptr.next.items[num] = new Node(false, R);
                }
            }
            ptr = (Node) ptr.next.items[num];
            if (i == key.length() - 1) {
                ptr.isKey = true;
            }
        }
    }

    /**
     * helper method for collecting keys in the trie
     * colHelp(String s, List<String> x, Node n):
     * If n.isKey, then x.add(s).
     * For character c in n.next.keys():
     * Call colHelp(s + c, x, n.next.get(c))
     */
    private List<String> colHelp(String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }
        for (int i = 0; i < R; i++) {
            Node p = (Node) n.next.items[i];
            if (p != null) {
                colHelp(s + (char) i, x, p);
            }
        }
        return x;
    }

    /**
     * Algorithm:
     * Find the node α corresponding to the string (in pink).
     * Create an empty list x.
     * For character c in α.next.keys():
     * Call colHelp(“sa” + c, x, α.next.get(c)).
     * @param prefix
     * @return
     */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new ArrayList<String>();
        Node ptr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int num = prefix.charAt(i);
            if (ptr.next.items[num] == null) {
                return null;
            }
            ptr = (Node) ptr.next.items[num];
        }
        for (int i = 0; i < R; i++) {
            Node p = (Node) ptr.next.items[i];
            if (p != null) {
                colHelp(prefix + (char) i, res, p);
            }
        }
        return res;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {

        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        String[] otherStrings = new String[]{"a", "awls", "hello"};

        MyTrieSet t = new MyTrieSet();
        for (String s: saStrings) {
            t.add(s);
        }
        for (String s: otherStrings) {
            t.add(s);
        }
        List<String> keys = t.keysWithPrefix("sa");
        System.out.println(keys.get(3));
        for (String s: saStrings) {
            assertTrue(keys.contains(s));
        }
        for (String s: otherStrings) {
            assertFalse(keys.contains(s));
        }
    }
}
