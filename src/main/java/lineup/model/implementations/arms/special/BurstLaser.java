package lineup.model.implementations.arms.special;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.arms.BurstGun;
import lineup.model.implementations.projectiles.BurstLaserBeam;

/**
 * BurstLaser
 * @author Neil
 */
public class BurstLaser extends BurstGun {

  /**
   * Constructor.
   */
  public BurstLaser(Bunker bunker) {
    super(bunker, 5000, 5, 200, 6, 12);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    return new BurstLaserBeam(getOwner(), target.getCreep());
  }

  public String getName() {
    return "Burst Laser";
  }

  public int getCost() {
    return 90;
  }

  public String getDescription() {
    return "Laser that fires rapid laser bursts";
  }

  @Override
  public double getDPS() {
    double rate = (double)(5 * 1000)/(double)(5000 + (5*200));
    double d = 3;
    return rate * d;
  }

}
