package lineup.model;

import java.util.List;

public abstract class Arms extends PoweredItem implements Purchasable {

  /**
   * Constructor.
   * @param size
   * @param power
   */
  public Arms(int size, int power) {
    super(size, power);
  }
  
  public abstract List<Projectile> fire(Location location, List<Target> targets);
  public abstract boolean isReady();
  public abstract void cooldown(int elapsed);
  public abstract double getDPS();
  
}
