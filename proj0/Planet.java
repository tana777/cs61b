public class Planet {
  /** add 6 instance variables. */
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  static final double G = 6.67 * 10e-12;

  /** add 2 Planet constructors that can initialize an instance of the Planet class. */
  public Planet(double xP, double yP, double xV,
                double yV, double m, String img) {
                  xxPos = xP;
                  yyPos = yP;
                  xxVel = xV;
                  yyVel = yV;
                  mass = m;
                  imgFileName = img;
                }
  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  /** Planet class no main method since it's never run directl. Also, all methods should be non-static. */
  /** Calculate the distance between two planets. */
  public double calcDistance(Planet q) {
    double dx = q.xxPos - xxPos;
    double dy = q.yyPos - yyPos;
    double r2 = dx * dx + dy * dy;
    double value;
    double sr = r2 / 2;
    do {
      value = sr;
      sr = (value + r2 / value) / 2;
    } while ((value-sr) != 0);
    return sr;
  }

  /** Calculate the force exerted on the taken in planet by the given planet. */
  public double calcForceExertedBy(Planet q) {
    double F = (G * mass * q.mass) / (calcDistance(q) * calcDistance(q));
    return F;
  }

  /** Calculate the force exerted in the X and Y directions on taken in planet, respectively. */
  public double calcForceExertedByX(Planet q) {
    double dx = q.xxPos - xxPos;
    double Fx = (calcForceExertedBy(q) * dx) / calcDistance(q);
    return Fx;
  }
  public double calcForceExertedByY(Planet q) {
    double dy = q.yyPos - yyPos;
    double Fy = (calcForceExertedBy(q) * dy) / calcDistance(q);
    return Fy;
  }

  /** Calculate the net X and net Y force exerted by all planets
  from taken in array of planets upon the current Planet. */
  public double calcNetForceExertedByX(Planet[] allPlanets) {
    double FnetX = 0;
    for (Planet element : allPlanets) {
      if (this.equals(element)) {
        continue;
      }
      FnetX = FnetX + calcForceExertedByX(element);
    }
    return FnetX;
  }
  public double calcNetForceExertedByY(Planet[] allPlanets) {
    double FnetY = 0;
    for (Planet element : allPlanets) {
      if (this.equals(element)) {
        continue;
      }
      FnetY = FnetY + calcForceExertedByY(element);
    }
    return FnetY;
  }

  /** Calculate the planet's updated position and velocity instance variables. */
  public void update(double dt, double Fnetx, double Fnety) {
    double AccX = Fnetx / mass;
    double AccY = Fnety / mass;
    xxVel = xxVel + dt * AccX;
    yyVel = yyVel + dt * AccY;
    xxPos = xxPos + dt * xxVel;
    yyPos = yyPos + dt * yyVel;
  }

  /** Draw Planet. */
  public void draw() {
    String img_to_draw = "images/" + this.imgFileName;
    StdDraw.picture(this.xxPos,this.yyPos, img_to_draw);
  }

  }
