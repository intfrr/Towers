package lineup.model.implementations.projectiles;

import java.awt.Color;
import java.awt.Graphics;


import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.util.math.Vector2D;

public class MediumBullet extends Projectile {

  private static double velocity = 130.0; 
  private Color color = Color.YELLOW;
  
  public MediumBullet(Bunker owner, int x, int y, double bearing) {
    super(owner, new Vector2D(new Location(x, y), velocity, bearing), 1);
  }

  public void render(Graphics g) {
    g.setColor(color);
    double dx = getVector().getX() + Math.cos(getVector().getBearing()) * 5;
    double dy = getVector().getY() + Math.sin(getVector().getBearing()) * 5;
    g.drawLine((int)getVector().getX(), (int)getVector().getY(), (int)dx, (int)dy);
  }

  /**
   * Flies in a straight line.
   */
  public void update(int elapsed) {
    double time = (double)elapsed / 1000.0;
    getVector().translateTime(time);
  }
  
  @Override
  public String toString() {
    return "MediumBullet: " + getVector();
  }

  @Override
  public double getDamage() {
    return 2;
  }

}
