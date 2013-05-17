package lineup.model;

import java.util.List;

public abstract class TrackingSystem extends PoweredItem implements Purchasable {
  
  /**
   * Constructor.
   * @param size
   * @param power
   */
  public TrackingSystem(int size, int power) {
    super(size, power);
  }
  
  public abstract List<Target> getTargets(Location location, List<Creep> creeps);
  public abstract int getRange();
  
}
