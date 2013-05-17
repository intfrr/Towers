package lineup.model;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import lineup.util.math.Vector2D;
import lineup.world.Renderable;
import lineup.world.Updateable;

public abstract class Projectile implements Renderable, Updateable {

  private Vector2D vector;
  private int size;
  private Bunker owner;
  private int blast = 1;
  

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
  
  /**
   * Creates a projectile with a blast. Unless the <code>getBlast()</code>
   * method is overriden a default blast is a circle and is non-persistent.
   * @param owner
   * @param v
   * @param size the size of the projectile
   * @param blast the px size of the blast
   */
  public Projectile(Bunker owner, Vector2D v, int size, int blast) {
    this(owner, v, size);
    this.blast = blast;
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
  
  public void setSize(int size) {
    this.size = size;
  }
  
  public int getBlastSize() {
    return blast;
  }
  
  public Blast getBlast() {
    Shape shape = new Ellipse2D.Double(vector.getX() - size/2 - blast/2, vector.getY() - size/2 - blast/2, blast, blast);
    return new Blast(shape, owner, getDamage(), 40);
  }
  
  public abstract double getDamage();
}
