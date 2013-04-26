package lineup.model;

public class Target {

  private int priority;
  private Creep creep;
  
  
  public Target(int priority, Creep creep) {
    this.priority = priority;
    this.creep = creep;
  }

  public int getPriority() {
    return priority;
  }
  
  public void setPriority(int priority) {
    this.priority = priority;
  }

  public void setCreep(Creep creep) {
    this.creep = creep;
  }

  public Creep getCreep() {
    return creep;
  }
  
}
