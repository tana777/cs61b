package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Tana Gegen
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 3;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;
    private Set<K> kSet;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        kSet = new HashSet<>();
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hashVal = hash(key);
        if (hashVal >= buckets.length) return null;
        else if (buckets[hashVal].containsKey(key)) return buckets[hashVal].get(key);
        return null;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (loadFactor() >= MAX_LF) {
            int resizebucket = 2 * buckets.length;
            ArrayMap<K, V>[] res = new ArrayMap[resizebucket];
            for (int i = 0; i < res.length; i += 1) {
                res[i] = new ArrayMap<>();
            }
            for (K item: kSet) {
                V val = buckets[hash(item)].get(item);
                if (val != null) {
                    int newhash = Math.floorMod(key.hashCode(), resizebucket);
                    res[newhash].put(item, val);
                }
            }
            buckets = res;
        }
        int hashVal = hash(key);
        if (!buckets[hashVal].containsKey(key)) {
            size = size + 1;
        }
        buckets[hashVal].put(key, value);
        kSet.add(key);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return kSet;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> bstmap = new MyHashMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        int g1 = bstmap.get("hello");
        int s = bstmap.size();
        bstmap.put("hello", 100);
        Set ss = bstmap.keySet();
        Set ss2 = bstmap.keySet();

    }
}
