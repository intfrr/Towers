package lineup.model.implementations.projectiles;

import java.awt.Color;
import java.awt.Graphics;


import lineup.model.Blast;
import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.implementations.blasts.NapalmBlast;
import lineup.util.math.Vector2D;

public class NapalmShell extends Projectile {

  private static double velocity = 40.0; 
  private Color color = Color.GRAY;
  
  /**
   * Constructor.
   * @param owner
   * @param x
   * @param y
   * @param bearing
   */
  public NapalmShell(Bunker owner, int x, int y, double bearing) {
    super(owner, new Vector2D(new Location(x, y), velocity, bearing), 3, 15);
  }

  public void render(Graphics g) {
    g.setColor(color);
    g.fillOval((int)getVector().getX(), (int)getVector().getY(), getSize(), getSize());
  }

  
  
  @Override
  public Blast getBlast() {
    double x = getVector().getX() - getSize()/2.0;
    double y = getVector().getY() - getSize()/2.0;
    return new NapalmBlast(x, y, getOwner());
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
  public double getDamage() {
    return 0;
  }

}
