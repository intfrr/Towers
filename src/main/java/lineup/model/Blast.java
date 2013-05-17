package lineup.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ListIterator;

import lineup.world.World;

public class Blast {

  private Shape shape;
  private int maxTtl;
  private int ttl;
  private double damage;
  private boolean persistant;
  private double maxH = 0.166666;
  private Bunker owner;
  private World world = World.getInstance();
  
  /**
   * Constructor.
   * @param shape
   * @param owner
   * @param damage
   * @param ttl
   */
  public Blast(Shape shape, Bunker owner, double damage, int ttl) {
    this.shape = shape;
    this.owner = owner;
    this.damage = damage;
    this.ttl = ttl;
    this.maxTtl = ttl;
  }

  public Shape getShape() {
    return shape;
  }

  public boolean hasExpired() {
    return ttl <= 0;
  }
  
  public void update(int elapsed) {
    
    if (persistant || ttl == maxTtl) {
      ListIterator<Creep> crip = world.getCreeps().listIterator();
      while (crip.hasNext()) {
        Creep creep = crip.next();
        if (shape.intersects(creep.getBoundingRect())) {
          creep.setHealth(creep.getHealth() - calcDamage(elapsed));
          if (creep.getHealth() <= 0) {
            world.getPlayer().giveMoney(creep.getValue());
            owner.incrementKills();
            crip.remove();
          }
        }
      }
    }
    
    ttl -= elapsed;
  }

  
  /**
   * Calculates damage. Note that peristant blasts do <code>damage</code>
   * <i>per second</i> but non-persistant blasts hit for a <i>one off</i>
   * amount of <code>damage</code>.
   * @param elapsed
   * @return
   */
  public double calcDamage(int elapsed) {
    if (persistant) {
      double sec = (double)elapsed / 1000.0;
      return damage*sec;
    } else {
      return damage;
    }
  }

  public void render(Graphics2D g2) {
    g2.setColor(calcColor());
    g2.draw(shape);
  }

  private Color calcColor() {
    float h = (float)(maxH * ttl / maxTtl);
    return Color.getHSBColor(h, 1f, 1f);
  }
  
  public void setPersistant(boolean persistant) {
    this.persistant = persistant;
  }
  
  public void setDamage(double damage) {
    this.damage = damage;
  }
  
  public void setTtl(int ttl) {
    this.ttl = ttl;
    this.maxTtl = ttl;
  }
}
