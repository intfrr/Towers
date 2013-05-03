package lineup.model.implementations.trackers;

public class HighPowerBasicTracker extends NearestSingleTargetTracker {

  public HighPowerBasicTracker() {
    super(120);
  }

  public String getName() {
    return "High power radar";
  }

  public int getCost() {
    return 200;
  }

  public String getDescription() {
    return "A powerful long range radar that tracks the nearest enemy";
  }
  
}
