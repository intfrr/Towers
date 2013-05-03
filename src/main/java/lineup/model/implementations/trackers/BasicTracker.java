package lineup.model.implementations.trackers;

public class BasicTracker extends NearestSingleTargetTracker {

  public BasicTracker() {
    super(40);
  }

  public String getName() {
    return "Low power radar";
  }

  public int getCost() {
    return 30;
  }

  public String getDescription() {
    return "A short range radar that tracks the nearest enemy";
  }

}
