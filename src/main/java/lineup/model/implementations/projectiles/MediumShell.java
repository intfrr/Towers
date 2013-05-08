package lineup.model.implementations.projectiles;

import java.awt.Color;
import java.awt.Graphics;


import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.util.math.Vector2D;

public class MediumShell extends Projectile {

  private static double velocity = 65.0; 
  private Color color = Color.RED;
  
  public MediumShell(Bunker owner, int x, int y, double bearing) {
    super(owner, new Vector2D(new Location(x, y), velocity, bearing), 3);
  }

  public void render(Graphics g) {
    g.setColor(color);
    g.fillOval((int)getVector().getX(), (int)getVector().getY(), getSize(), getSize());
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
    return "MedShell: " + getVector();
  }

  @Override
  public int getDamage() {
    return 6;
  }

}
