package bearmaps;

import java.util.List;

/**
 * Project 2AB: Extrinsic PQ and KDTree (2019)
 *
 * Proj2B - K-d Tree
 *
 * @author Tana Gegen 8/4/20
 */
public class NaivePointSet implements PointSet {

    private List<Point> p;

    public NaivePointSet(List<Point> points) {
        p = points;
    }

    private static double distance(double x1, double x2, double y1, double y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    @Override
    public Point nearest(double x, double y) {
        Point first = p.get(0);
        double minD = distance(x, first.getX(), y, first.getY());
        for (Point o: p) {
            double dis = distance(x, o.getX(), y, o.getY());
            if (dis < minD) {
                minD = dis;
                first = o;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
    }
}
