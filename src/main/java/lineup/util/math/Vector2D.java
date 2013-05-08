package lineup.util.math;

import lineup.model.Location;

public class Vector2D {

  private double bearing;
  private double magnitude;
  private double x;
  private double y;
  
  /**
   * Constructor.
   * @param x
   * @param y
   * @param magnitude
   * @param bearing
   */
  public Vector2D(double x, double y, double magnitude, double bearing) {
    this.magnitude = magnitude;
    this.bearing = bearing;
    this.x = x;
    this.y = y;
  }
  
  /**
   * Constructor.
   * @param start
   * @param magnitude
   * @param bearing
   */
  public Vector2D(Location start, double magnitude, double bearing) {
    this(start.x, start.y, magnitude, bearing);
  }
  
  
  /**
   * Constructor.
   * @param start
   * @param end
   */
  public Vector2D(Location start, Location end) {
    this.x = start.x;
    this.y = start.y;
    
    double dx = end.x - start.x;
    double dy = end.y - start.y;
    
    this.magnitude = Math.sqrt(dx*dx + dy*dy);
    this.bearing = Math.atan2(dy, dx);
  }
  
  /**
   * Constructor.
   * @param start
   * @param pointingAt
   * @param magnitude
   */
  public Vector2D(Location start, Location pointingAt, double magnitude) {
    this.x = start.x;
    this.y = start.y;
    
    double dx = pointingAt.x - start.x;
    double dy = pointingAt.y - start.y;
    
    this.magnitude = magnitude;
    this.bearing = Math.atan2(dy, dx);
  }
  
  @Override
  public String toString() {
    return x + "," + y + " b:" + bearing + " m:" + magnitude;
  }
  
  public double getBearing() {
    return bearing;
  }
  
  public void setBearing(double bearing) {
    this.bearing = bearing;
  }

  public double getMagnitude() {
    return magnitude;
  }
  
  public void setX(double x) {
    this.x = x;
  }
  
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Returns the x position of the vector origin.
   * @return
   */
  public double getX() {
    return x;
  }

  /**
   * Returns the y position of the vector origin.
   * @return
   */
  public double getY() {
    return y;
  }
  
  public double getDx() {
    return magnitude * Math.cos(bearing);
  }
  
  public double getDy() {
    return magnitude * Math.sin(bearing);
  }

  public void translateTime(double elapsed) {
    double dx = getDx() * elapsed;
    double dy = getDy() * elapsed;
    
    x = x + dx;
    y = y + dy; 
  }
  
  public void pointAt(Location target) {
    double dx = target.x - x;
    double dy = target.y - y;
    bearing = Math.atan2(dy, dx);
  }

  public Location getLocation() {
    return new Location(x, y);
  }

  public void translate(double dx, double dy) {
    x += dx;
    y += dy;
  }

  public void add(Vector2D v) {
    double dx = getDx() + v.getDx();
    double dy = getDy() + v.getDy();
    magnitude = Math.sqrt(dx*dx + dy*dy);
    
    Location end = new Location(x + dx, y + dy);
    pointAt(end);
  }

  public void scale(int scale) {
    magnitude = magnitude * scale;
  }
  
  public Vector2D copy() {
    return new Vector2D(x, y, magnitude, bearing);
  }

  public void turn(double delta) {
    bearing += delta;
  }
}
