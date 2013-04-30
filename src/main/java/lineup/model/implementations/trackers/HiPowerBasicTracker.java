package lineup.model.implementations.trackers;

public class HiPowerBasicTracker extends NearestSingleTargetTracker {

  public HiPowerBasicTracker() {
    super(100);
  }

  public String getName() {
    return "Microwave Radar";
  }

  public int getCost() {
    return 80;
  }

}
