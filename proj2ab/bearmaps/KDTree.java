package bearmaps;

import java.util.List;

/**
 * Part 2: K-d Tree
 *
 * @author Tana Gegen 8/4/20
 */
public class KDTree implements PointSet {

    private Node root;

    public KDTree(List<Point> points) {
        root = new Node(points.get(0).getX(), points.get(0).getY());
        for (int i = 1; i < points.size(); i++) {
            root.put(points.get(i).getX(), points.get(i).getY());
        }
    }


    private class Node {
        /*
        (X, Y) pair stored in this Node.
         */
        private double xloc;
        private double yloc;

        /*
        Children of this Node.
         */
        private Node left;
        private Node right;

        private Node(double x, double y) {
            this.xloc = x;
            this.yloc = y;
        }

        private Node putHelper(double x, double y, Node root, int depth) {
            if (root == null) {
                return new Node(x, y);
            }
            if (depth % 2 == 0) {
                int xcmp = Double.compare(x, root.xloc);
                if (xcmp < 0) {
                    root.left = putHelper(x, y, root.left, depth + 1);
                } else {
                    root.right = putHelper(x, y, root.right, depth + 1);
                }
            } else {
                int ycmp = Double.compare(y, root.yloc);
                if (ycmp < 0) {
                    root.left = putHelper(x, y, root.left, depth + 1);
                } else {
                    root.right = putHelper(x, y, root.right, depth + 1);
                }
            }
            return root;
        }

        private void put(double x, double y) {
            root = putHelper(x, y, root, 0);
        }

    }


    /**
     * nearest(Node n, Point goal, Node best):
     * If n is null, return best
     * If n.distance(goal) < best.distance(goal), best = n
     * best = nearest(n.leftChild, goal, best)
     * best = nearest(n.rightChild, goal, best)
     * return best
     */

    public Node findNearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(new Point(n.xloc, n.yloc), goal)
                < Point.distance(new Point(best.xloc, best.yloc), goal)) {
            best = n;
        }
        best = findNearest(n.left, goal, best);
        best = findNearest(n.right, goal, best);
        return best;
    }

    /**
     * If n is null, return best
     * If n.distance(goal) < best.distance(goal), best = n
     * If goal < n (according to n’s comparator):
     * goodSide = n.”left”Child
     * badSide = n.”right”Child
     * else:
     * goodSide = n.”right”Child
     * badSide = n.”left”Child
     * best = nearest(goodSide, goal, best)
     * If bad side could still have something useful
     * best = nearest(badSide, goal, best)
     * return best
     * @return
     */
    public Node optfindNearest(Node n, Point goal, Node best, int depth) {
        Node good;
        Node bad;
        if (n == null) {
            return best;
        }
        if (Point.distance(new Point(n.xloc, n.yloc), goal)
                < Point.distance(new Point(best.xloc, best.yloc), goal)) {
            best = n;
        }
        if (depth % 2 == 0) {
            if (Double.compare(goal.getX(), n.xloc) < 0) {
                good = n.left;
                bad = n.right;
            } else {
                good = n.right;
                bad = n.left;
            }
        } else {
            if (Double.compare(goal.getY(), n.yloc) < 0) {
                good = n.left;
                bad = n.right;
            } else {
                good = n.right;
                bad = n.left;
            }
        }
        best = optfindNearest(good, goal, best, depth + 1);
        double dist = Point.distance(new Point(best.xloc, best.yloc), goal);
        if (bad != null) {
            if (depth % 2 == 0) {
                double estimate = (bad.xloc - goal.getX()) * (bad.xloc - goal.getX());
                if (estimate < dist) {
                    best = optfindNearest(bad, goal, best, depth + 1);
                }
            } else {
                double estimate = (bad.yloc - goal.getY()) * (bad.yloc - goal.getY());
                if (estimate < dist) {
                    best = optfindNearest(bad, goal, best, depth + 1);
                }
            }
        }
        return best;
    }

    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node best = root;
//        Node n = findNearest(root, goal, best);
        Node n = optfindNearest(root, goal, best, 0);
        return new Point(n.xloc, n.yloc);
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);

        KDTree kt = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point nearest = kt.nearest(0, 7);
    }
}
