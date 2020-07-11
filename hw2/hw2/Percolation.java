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
    private int[] status;
    private WeightedQuickUnionUF uf;
    private int len;
    private int openSize;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        len = N;
        status = new int[N*N];
        uf = new WeightedQuickUnionUF(N*N);
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
        helper method to allocate location number for two-dimension array loc.
     */
    private int[][] assignLoc() {
        int num = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                loc[i][j] = num;
                num = num + 1;
            }
        }
        return loc;
    }
    /*
        helper method to translate 2D to 1D.
     */
    private int xyTo1D(int row, int col){
        return loc[row][col];
    }

    /*
        helper method to check its neighbours' status
     */
    private void unionNeighbours(int l) {
        if (l - len >= 0 && status[l - len] == 1 && !uf.connected(l, l - len)) {
            uf.union(l, l - len);
        }
        else if (l + len <= len * len - 1 && status[l + len] == 1 && !uf.connected(l, l + len)) {
            uf.union(l, l + len);
        }
        else if (l - 1 >= 0 && status[l - 1] == 1 && !uf.connected(l, l - 1)) {
            uf.union(l, l - 1);
        }
        else if (l + 1 <= len * len -1 && status[l + 1] == 1 && !uf.connected(l, l + 1)) {
            uf.union(l, l + 1);
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
        status[l] = 1;
        openSize = openSize + 1;
        unionNeighbours(l);

    }

    /*
        is the site (row, col) open?
     */
    public boolean isOpen(int row, int col){
        int l = xyTo1D(row, col);
        if (l < 0 || l > len * len) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(status[l] == 1) {
            return true;
        }
        return false;
    }

    /*
        is the site (row, col) full?
     */
    public boolean isFull(int row, int col){
        int location = xyTo1D(row, col);
        if (location < 0 || location > len * len) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return (location <= len -1) || (uf.find(location) <= len - 1);
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
        for (int i = len * (len - 1); i < len * len; i++) {
            if (uf.find(i) <= len - 1) {
                return true;
            }
        }
        return false;
    }

    /*
        use for unit testing (not required)
     */
    public static void main(String[] args) {
        Percolation rock = new Percolation(3);
        rock.open(0, 0);
        boolean f1 = rock.isFull(0, 0);
        rock.open(0, 1);
        boolean f2 = rock.isFull(0, 1);
        boolean p1 = rock.percolates();
        rock.open(1, 1);
        boolean p2 = rock.percolates();
        rock.open(2, 1);
        boolean p3 = rock.percolates();


    }

}
