package lineup.model;

import java.awt.Point;

public class Location {

  public double x;
  public double y;
  
  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public Location(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public Point asPoint() {
    return new Point((int)x, (int)y);
  }

  @Override
  public String toString() {
    return x + "," + y;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Location other = (Location) obj;
    if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
      return false;
    if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
      return false;
    return true;
  }
  
}
