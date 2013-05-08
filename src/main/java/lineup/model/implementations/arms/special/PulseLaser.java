package lineup.model.implementations.arms.special;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.PulseLaserBeam;
import lineup.model.implementations.arms.SingleShotGun;

/**
 * Pulse Laser
 * @author Neil
 */
public class PulseLaser extends SingleShotGun {

  /**
   * Constructor.
   */
  public PulseLaser(Bunker bunker) {
    super(bunker, 5000);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    return new PulseLaserBeam(getOwner(), target.getCreep());
  }

  public String getName() {
    return "Pulse Laser";
  }

  public int getCost() {
    return 50;
  }

  public String getDescription() {
    return "Has a long reload, but fires a powerful beam";
  }

}
