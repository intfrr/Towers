package lineup.model.implementations.projectiles;

import java.awt.Color;
import java.awt.Graphics;


import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.util.math.Vector2D;
import lineup.world.World;

public class Grenade extends Projectile {

  public static final double VELOCITY = 40.0;
  private Color color = new Color(100, 220, 100);
  
  private boolean landed;
  private int ttl;
  
  /**
   * Constructor.
   * @param owner
   * @param x
   * @param y
   * @param bearing
   * @param ttl
   */
  public Grenade(Bunker owner, int x, int y, double bearing, int ttl) {
    super(owner, new Vector2D(new Location(x, y), VELOCITY, bearing), 2, 8);
    this.ttl = ttl;
  }

  public void render(Graphics g) {
    g.setColor(color);
    g.fillOval((int)getVector().getX(), (int)getVector().getY(), getSize(), getSize());
  }

  /**
   * Flies in a straight line.
   */
  public void update(int elapsed) {
    ttl -= elapsed;
    if (ttl <= 0) {
      if (landed) {
        explode();
      } else {
        landed = true;
        ttl = 2000;
        getVector().scale(0);
      }
    } else {
      double time = (double)elapsed / 1000.0;
      getVector().translateTime(time);
    }
  }
  
  private void explode() {
    World.getInstance().getBlasts().add(getBlast());
    getVector().translate(-100000, 0);
  }
  
  @Override
  public String toString() {
    return "Grenade: " + getVector();
  }

  @Override
  public double getDamage() {
    return 2;
  }

}
