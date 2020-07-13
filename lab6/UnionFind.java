package lab6;

public class UnionFind {

    private int length;
    private int[] parents;
    private int[] sizes;


    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        length = n;
        parents = new int[length];
        sizes = new int[length];
        for (int i = 0; i < length; i++) {
            parents[i] = -1;
            sizes[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= length) {
            throw new IndexOutOfBoundsException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        return sizes[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        if (parents[v1] == -1) {
            return -1 * sizes[v1];
        }
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 != root2) {
            if (sizeOf(root1) >= sizeOf(root2)) {
                parents[root2] = root1;
                sizes[root1] = sizes[root1] + sizes[root2];
            } else {
                parents[root1] = root2;
                sizes[root2] = sizes[root2] + sizes[root1];
            }
        }
    }

    /*
        helper method to compute the root for a given vertex
     */
    private int findRoot(int vertex) {
        while (parents[vertex] != -1) {
            vertex = parents[vertex];
        }
        return vertex;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (parents[vertex] == -1) {
            return vertex;
        }
        int root = findRoot(vertex);
        int p = vertex;
        while (parents[p] != -1) {
            union(root, p);
            p = parents[p];
        }
        return root;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        uf.union(1, 4);
        uf.union(2, 3);
        uf.union(0, 1);
        int f1 = uf.find(1);
        uf.union(2,1);

    }

}