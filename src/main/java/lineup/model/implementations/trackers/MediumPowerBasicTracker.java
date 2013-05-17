package lineup.model.implementations.trackers;

public class MediumPowerBasicTracker extends NearestSingleTargetTracker {

  public MediumPowerBasicTracker() {
    super(80, 4, 6);
  }

  public String getName() {
    return "Medium radar";
  }

  public int getCost() {
    return 80;
  }

  public String getDescription() {
    return "A medium range radar that tracks the nearest enemy";
  }
  
}
