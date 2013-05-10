package lineup.util;

import java.util.Comparator;

import lineup.model.Creep;

public class NearestBaseComparator implements Comparator<Creep> {

  public int compare(Creep c1, Creep c2) {

    int r1 = c1.getRoute().size();
    int r2 = c2.getRoute().size();
    
    if (r1 != r2) {
      return r1 < r2 ? -1 : 1;
    }
    
    double d1 = c1.getDistanceToNextRoutePoint();
    double d2 = c2.getDistanceToNextRoutePoint();
    return d1 < d2 ? -1 : 1;
  }

}
