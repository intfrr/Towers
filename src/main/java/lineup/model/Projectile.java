package lineup.model;

import java.awt.Rectangle;

import lineup.world.Renderable;
import lineup.world.Updateable;

public abstract class Projectile implements Renderable, Updateable {

  private double x;
  private double y;
  private double velocity;
  private double bearing;
  private int size;
  private Bunker owner;
  
  /**
   * Constructor.
   * @param x
   * @param y
   * @param velocity
   * @param bearing
   */
  public Projectile(Bunker owner, int x, int y, double velocity, double bearing, int size) {
    this.owner = owner;
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    this.bearing = bearing;
    this.size = size;
  }
  
  public Bunker getOwner() {
    return owner;
  }
  
  public double getX() {
    return x;
  }
  
  public void setX(double x) {
    this.x = x;
  }
  
  public double getY() {
    return y;
  }
  
  public void setY(double y) {
    this.y = y;
  }
  
  public double getVelocity() {
    return velocity;
  }
  
  public void setVelocity(double velocity) {
    this.velocity = velocity;
  }
  
  public double getBearing() {
    return bearing;
  }
  
  public void setBearing(double bearing) {
    this.bearing = bearing;
  }
  
  public Rectangle getBoundingRect() {
    return new Rectangle((int)x, (int)y, size, size);
  }
  
  public int getSize() {
    return size;
  }
  
  public abstract int getDamage();
}
