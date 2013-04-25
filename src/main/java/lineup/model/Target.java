package lineup.model;

public class Target {

  private int priority;
  private Location location;
  
  
  public Target(int priority, Location location) {
    this.priority = priority;
    this.location = location;
  }

  public int getPriority() {
    return priority;
  }
  
  public void setPriority(int priority) {
    this.priority = priority;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
  
}
