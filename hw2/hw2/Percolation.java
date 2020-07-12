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
    private int len;
    private int openSize;
    private int virtualtop;
    private int virtualbottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        virtualtop = N * N;
        virtualbottom = N * N + 1 ;
        len = N;
        status = new boolean[N*N];
        uf = new WeightedQuickUnionUF(N*N + 2);
        connectVirtual();
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
        }
        for (int i = len * (len - 1); i < len * len; i++) {
            uf.union(virtualbottom, i);
        }
    }

    /*
        helper method to solve backwash problem
     */


    /*
        helper method to translate 2D to 1D.
     */
    private int xyTo1D(int row, int col){
        return loc[row][col];
    }

    /*
        helper method to check its neighbours' status
     */
    private void unionNeighbours(int row, int col) {
        int current = xyTo1D(row, col);

        if (row + 1 < len) {
            int up = xyTo1D(row + 1, col);
            if (status[up] && !uf.connected(current, up)) {
                uf.union(current, up);
            }
        }
        if (row - 1 >= 0) {
            int down = xyTo1D(row - 1, col);
            if (status[down] && !uf.connected(current, down)) {
                uf.union(current, down);
            }
        }
        if (col - 1 >= 0) {
            int left = xyTo1D(row, col - 1);
            if (status[left] && !uf.connected(current, left)) {
                uf.union(current, left);
            }
        }
        if (col + 1 < len) {
            int right = xyTo1D(row, col + 1);
            if (status[right] && !uf.connected(current, right)) {
                uf.union(current, right);
            }
        }
    }

    /*
         open the site (row, col) if it is not open already
     */
    public void open(int row, int col){
        int l = xyTo1D(row, col);
        if (l < 0 || l > len * len) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!status[l]) {
            status[l] = true;
            openSize = openSize + 1;
            unionNeighbours(row, col);

        }
    }

    /*
        is the site (row, col) open?
     */
    public boolean isOpen(int row, int col){
        int l = xyTo1D(row, col);
        if (l < 0 || l > len * len) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return status[l];
    }


    /*
        is the site (row, col) full?
     */
    public boolean isFull(int row, int col){
        int location = xyTo1D(row, col);
        if (location < 0 || location > len * len) {
            throw new java.lang.IndexOutOfBoundsException();
        }
//        if (status[location]) {
//            for (int l = 0; l < len; l++) {
//                if (uf.connected(l, location)) {
//                    return true;
//                }
//            }
//        }
        return status[location] && uf.connected(location, virtualtop);


    }

    /*
        number of open sites
     */
    public int numberOfOpenSites(){
        return openSize;
    }

    /*
        does the system percolate?
     */
    public boolean percolates(){
//        for (int up = 0; up < len; up++) {
//            for (int down = len * (len - 1); down < len * len; down++) {
//                if (uf.connected(up, down)) {
//                    return true;
//                }
//            }
//        }
//        return false;
        return uf.connected(virtualtop, virtualbottom);
    }

    /*
        use for unit testing (not required)
     */
//    public static void main(String[] args) {
//        Percolation rock = new Percolation(3);
//        rock.open(0, 0);
//        boolean f1 = rock.isFull(0, 0);
//        rock.open(0, 1);
//        boolean f2 = rock.isFull(0, 1);
//        boolean p1 = rock.percolates();
//        rock.open(1, 1);
//        boolean p2 = rock.percolates();
//        rock.open(2, 1);
//        boolean p3 = rock.percolates();
//    }

}
