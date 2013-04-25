package lineup.model;

public class Wave {

  private int interval;
  private int count;
  private int remaining;
  private Creep creep;
  private int cooldown;
  
  public Wave(int interval, int count, Creep creep) {
    this.interval = interval * 1000;
    this.cooldown = interval;
    this.count = count;
    this.remaining = count;
    this.creep = creep;
  }
  
  public boolean spawnRequired(int elapsed) {
    //System.out.println("cooldown:" + cooldown);
    if (cooldown > 0) {
      if (cooldown > elapsed) {
        cooldown = cooldown - elapsed;
      } else {
        cooldown = 0;
      }
    }
    return cooldown == 0;
  }
  
  public Creep spawn() {
    if (remaining == 0) {
      throw new IllegalStateException("Cannot spawn, none remaining in this wave.");
    }
    remaining--;
    cooldown = interval;
    return creep.copy();
  }
  
  @Override
  public String toString() {
    return count + " " + creep.getName();
  }

  public int getInterval() {
    return interval;
  }

  public void setInterval(int interval) {
    this.interval = interval;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public Creep getCreep() {
    return creep;
  }

  public void setCreep(Creep creep) {
    this.creep = creep;
  }

  public int getRemaining() {
    return remaining;
  }
  
}
