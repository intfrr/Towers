package lineup.model.implementations.trackers;

public class HiPowerBasicTracker extends NearestSingleTargetTracker {

  public HiPowerBasicTracker() {
    super(100);
  }

  @Override
  public String getName() {
    return "Microwave Radar";
  }

}
