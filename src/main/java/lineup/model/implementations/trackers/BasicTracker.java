package lineup.model.implementations.trackers;

public class BasicTracker extends NearestSingleTargetTracker {

  public BasicTracker() {
    super(40);
  }

  public String getName() {
    return "Basic tracker";
  }

  public int getCost() {
    return 30;
  }

}
