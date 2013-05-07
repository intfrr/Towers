package lineup.model;

import java.awt.Rectangle;

import lineup.util.math.Vector2D;
import lineup.world.Renderable;
import lineup.world.Updateable;

public abstract class Projectile implements Renderable, Updateable {

  private Vector2D vector;
  private int size;
  private Bunker owner;
  

  /**
   * @param owner
   * @param v
   * @param size
   */
  public Projectile(Bunker owner, Vector2D v, int size) {
    this.owner = owner;
    this.vector = v;
    this.size = size;
  }
  
  public Bunker getOwner() {
    return owner;
  }
  
  public Vector2D getVector() {
    return vector;
  }
  
  public Rectangle getBoundingRect() {
    return new Rectangle((int)vector.getX(), (int)vector.getY(), size, size);
  }
  
  public int getSize() {
    return size;
  }
  
  public abstract int getDamage();
}
