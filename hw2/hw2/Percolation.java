package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/** HW 2: Percolation
 *
 *  @author tanagegen 07/09/2020
 *
 */

public class Percolation {
    /*
        create N-by-N grid, with all sites initially blocked
    */
    private int[][] loc;
    private boolean[] status;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int len;
    private int openSize;
    private int virtualtop;
    private int virtualbottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        virtualtop = N * N;
        virtualbottom = N * N + 1;
        len = N;
        status = new boolean[N * N + 2];
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF((N * N + 1));
        if (len >= 2) {
            connectVirtual();
        }
        openSize = 0;
        int num = 0;
        loc = new int[N][N];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                loc[i][j] = num;
                num = num + 1;
            }
        }

    }

    /*
        helper method to connect the top row with virtual top
        and connect the bottom row with virtual bottom.
     */
    private void connectVirtual() {
        for (int i = 0; i < len; i++) {
            uf.union(virtualtop, i);
            uf2.union(virtualtop, i);
        }
        if (len > 2) {
            for (int i = len * (len - 1); i < len * len; i++) {
                uf.union(virtualbottom, i);
            }
        }

    }
    /*
        helper method to translate 2D to 1D.
     */
    private int xyTo1D(int row, int col) {
        return loc[row][col];
    }

    /*
        helper method to check its neighbours' status
     */
    private void unionNeighbours(int row, int col) {
        int current = xyTo1D(row, col);

        if (row + 1 < len) {
            int up = xyTo1D(row + 1, col);
            if (status[up] && (!uf.connected(current, up) || !uf.connected(current, up))) {
                uf.union(current, up);
                uf2.union(current, up);
            }
        }
        if (row - 1 >= 0) {
            int down = xyTo1D(row - 1, col);
            if (status[down] && (!uf.connected(current, down) || !uf2.connected(current, down))) {
                uf.union(current, down);
                uf2.union(current, down);
            }
        }
        if (col - 1 >= 0) {
            int left = xyTo1D(row, col - 1);
            if (status[left] && (!uf.connected(current, left) || !uf2.connected(current, left))) {
                uf.union(current, left);
                uf2.union(current, left);
            }
        }
        if (col + 1 < len) {
            int right = xyTo1D(row, col + 1);
            if (status[right] && (!uf.connected(current, right)
                    || !uf2.connected(current, right))) {
                uf.union(current, right);
                uf2.union(current, right);
            }
        }
    }

    /*
        helper method to validate location l
     */
    private void validate(int l) {
        if (l < 0 || l > len * len) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    /*
         open the site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        int location = xyTo1D(row, col);
        validate(location);
        if (!status[location]) {
            status[location] = true;
            openSize = openSize + 1;
            unionNeighbours(row, col);
        }
    }

    /*
        is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        int location = xyTo1D(row, col);
        validate(location);
        return status[location];
    }


    /*
        is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        int location = xyTo1D(row, col);
        validate(location);
        if (len == 1 && status[location]) {
            return true;
        }
        return status[location] && uf2.connected(location, virtualtop);
    }

    /*
        number of open sites
     */
    public int numberOfOpenSites() {
        return openSize;
    }

    /*
        does the system percolate?
     */
    public boolean percolates() {
//        for (int up = 0; up < len; up++) {
//            for (int down = len * (len - 1); down < len * len; down++) {
//                if (uf.connected(up, down)) {
//                    return true;
//                }
//            }
//        }
//        return false;
        if (len == 1 && openSize == 1) {
            return true;
        }
        else if (len == 1 && openSize == 0) {
            return false;
        }
        else if (len == 2) {
            for (int i = len; i < len * len; i++) {
                return uf.connected(virtualtop, i);
            }
            return false;
        }
        return uf.connected(virtualtop, virtualbottom);
    }

    /*
        use for unit testing (not required)
     */
    public static void main(String[] args) {
        Percolation rock = new Percolation(2);
        rock.open(0, 0);
        boolean f1 = rock.isFull(0, 0);
        rock.open(1, 1);
        boolean f2 = rock.isFull(1, 1);
        boolean p1 = rock.percolates();
        rock.open(1, 0);
        boolean f4 = rock.isFull(1, 0);
        boolean p3 = rock.percolates();
    }

}
