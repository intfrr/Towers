package lineup.model;

import java.util.List;

public interface TrackingSystem extends Purchasable {
  
  public List<Target> getTargets(Location location, List<Creep> creeps);
  public int getRange();
  
}
