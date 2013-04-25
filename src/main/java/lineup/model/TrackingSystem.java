package lineup.model;

import java.util.List;

public interface TrackingSystem {
  
  public List<Target> getTargets(Location location, List<Creep> creeps);
  public String getName();
  public int getRange();
}
