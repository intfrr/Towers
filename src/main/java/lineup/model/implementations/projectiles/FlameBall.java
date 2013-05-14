package lineup.model.implementations.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.util.math.Vector2D;

public class FlameBall extends Projectile {

  private static double velocity = 40.0;
  private double maxTtl = 1800.0;
  private double ttl;
  private double maxSize = 12.0;
  private double maxH = 0.166666;
  
  /**
   * Constructor.
   * @param owner
   * @param x
   * @param y
   * @param bearing
   */
  public FlameBall(Bunker owner, int x, int y, double bearing) {
    super(owner, new Vector2D(new Location(x, y), velocity, bearing), 1);
    this.ttl = maxTtl; 
  }

  public void render(Graphics g) {
    g.setColor(calcColor());
    g.fillOval((int)getVector().getX(), (int)getVector().getY(), getSize(), getSize());
  }

  private Color calcColor() {
    float h = (float)(maxH * ttl / maxTtl);
    return Color.getHSBColor(h, 1f, 1f);
  }

  public void update(int elapsed) {
    ttl -= elapsed;
    if (ttl <= 0) {
      //Better way of destroying projectile than simply setting it to be off screen?
      getVector().setX(200000);
    } else {
      setSize((int)(maxSize * (maxTtl - ttl) / maxTtl));
      double time = (double)elapsed / 1000.0;
      getVector().translateTime(time);
    }
  }

  @Override
  public String toString() {
    return "FlameBall: ttl: " + ttl;
  }
  
  @Override
  public double getDamage() {
    return 0.1;
  }

}
