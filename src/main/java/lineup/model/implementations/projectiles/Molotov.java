package lineup.model.implementations.projectiles;

import java.awt.Color;
import java.awt.Graphics;


import lineup.model.Blast;
import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.implementations.blasts.MolotovBlast;
import lineup.util.math.Vector2D;
import lineup.world.World;

public class Molotov extends Projectile {

  public static double VELOCITY = 40.0; 
  private Color color = Color.ORANGE;
  
  private int ttl;
  
  /**
   * Constructor.
   * @param owner
   * @param x
   * @param y
   * @param bearing
   * @param ttl
   */
  public Molotov(Bunker owner, int x, int y, double bearing, int ttl) {
    super(owner, new Vector2D(new Location(x, y), VELOCITY, bearing), 2, 10);
    this.ttl = ttl;
  }

  public void render(Graphics g) {
    g.setColor(color);
    g.fillOval((int)getVector().getX(), (int)getVector().getY(), getSize(), getSize());
  }

  
  
  @Override
  public Blast getBlast() {
    double x = getVector().getX() - getSize()/2.0;
    double y = getVector().getY() - getSize()/2.0;
    return new MolotovBlast(x, y, getOwner());
  }

  /**
   * Flies in a straight line.
   */
  public void update(int elapsed) {
    ttl -= elapsed;
    if (ttl <= 0) {
      explode();
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
    return "Molotov: " + getVector();
  }

  @Override
  public double getDamage() {
    return 0.1;
  }

}
