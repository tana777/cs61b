public class NBody {

  public static double readRadius(String file) {
    In in = new In(file);
    int N = in.readInt();
    double R = in.readDouble();
    return R;
  }

  public static Planet[] readPlanets(String file) {
    In in = new In(file);
    int N = in.readInt();
    double R = in.readDouble();
    Planet[] Arrayofplanets = new Planet[N];
    int i = 0;
    while (i < N) {
      double xx = in.readDouble();
      double yy = in.readDouble();
      double xxV = in.readDouble();
      double yyV = in.readDouble();
      double mass = in.readDouble();
      String imgName = in.readString();
      Arrayofplanets[i] = new Planet(xx,yy,xxV,yyV,mass,imgName);
      i = i + 1;
    }
    return Arrayofplanets;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double r = readRadius(filename);
    Planet[] array_of_planets = readPlanets(filename);
    int num = array_of_planets.length;
    /** set the scale. */
    String backgroundimage = "images/starfield.jpg";
    StdDraw.setScale(-r*1.5, r*1.5);
    StdDraw.picture(-0, 0, backgroundimage);
    for (Planet p : array_of_planets) {
      p.draw();
    }
    StdDraw.enableDoubleBuffering();
    for (double timeV = 0; timeV <= T; timeV++) {
      double[] xForces = new double[num];
      double[] yForces = new double[num];
      for (int i = 0; i < num; i++) {
        xForces[i] = array_of_planets[i].calcNetForceExertedByX(array_of_planets);
        yForces[i] = array_of_planets[i].calcNetForceExertedByY(array_of_planets);
      }
      for (int i = 0; i < num; i++) {
        array_of_planets[i].update(timeV, xForces[i], yForces[i]);
      }
      StdDraw.picture(-0, 0, backgroundimage);
      for (Planet p : array_of_planets) {
        p.draw();
      }
      StdDraw.enableDoubleBuffering();
      StdDraw.show();
      StdDraw.pause(10);
      timeV = timeV + dt;
    }
    StdOut.printf("%d\n", num);
    StdOut.printf("%.2e\n", r);
    for (int i = 0; i < num; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
      array_of_planets[i].xxPos, array_of_planets[i].yyPos, array_of_planets[i].xxVel,
      array_of_planets[i].yyVel, array_of_planets[i].mass, array_of_planets[i].imgFileName);
    }
  }

  }
