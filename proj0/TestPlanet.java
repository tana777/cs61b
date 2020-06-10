/**
 *  Tests the Planet class.
 */
public class TestPlanet {

    /**
     *  Tests the Planet constructor to make sure it's working correctly.
     */
    public static void main(String[] args) {
      double xxPos = 1.0,
             yyPos = 2.0,
             xxVel = 3.0,
             yyVel = 4.0,
             mass = 5.0;
      String imgFileName = "jupiter.gif";
      Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
      Planet pCopy = new Planet(p);
      p.update(10,-100,30);
    }

}
