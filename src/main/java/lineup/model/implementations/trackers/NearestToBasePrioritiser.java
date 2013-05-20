package lineup.model.implementations.trackers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import lineup.model.Creep;
import lineup.model.Location;
import lineup.model.Target;
import lineup.model.TrackingSystem;
import lineup.util.NearestBaseComparator;

public abstract class NearestToBasePrioritiser extends TrackingSystem {

  private int range;

  /**
   * Constructor.
   * @param size
   * @param power
   * @param range
   */
  public NearestToBasePrioritiser(int range, int size, int power) {
    super(size, power);
    this.range = range;
  }

  @Override
  public List<Target> getTargets(Location location, List<Creep> creeps) {
    if (creeps.isEmpty()) {
      return Collections.emptyList();
    }
    List<Target> targets = new ArrayList<Target>();

    SortedSet<Creep> sorted = new TreeSet<Creep>(new NearestBaseComparator());
    for (Creep c : creeps) {
      if (inRange(location, c)) {
        sorted.add(c);
      }
    }
    if (!sorted.isEmpty()) {
      targets.add(new Target(1, sorted.first()));
    }
    
    return targets;
  }
  
  private boolean inRange(Location me, Creep nearest) {
    double d = me.asPoint().distance(nearest.getCentreLocation().asPoint());
    return d <= range;
  }

  @Override
  public int getRange() {
    return range;
  }

}
