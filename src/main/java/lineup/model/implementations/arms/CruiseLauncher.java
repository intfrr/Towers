package lineup.model.implementations.arms;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.CruiseMissile;
import lineup.model.implementations.arms.SingleShotGun;

/**
 * Missile launcher with 8 sec reload that fires cruise
 * missiles.
 * @author 38183Ne
 */
public class CruiseLauncher extends SingleShotGun {

  /**
   * Constructor.
   */
  public CruiseLauncher(Bunker bunker) {
    super(bunker, 8000);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    double dx = target.getCreep().getCentreLocation().x - location.x;
    double dy = target.getCreep().getCentreLocation().y - location.y;
    double bearing = Math.atan2(dy, dx);
    
    return new CruiseMissile(getOwner(), (int)location.x, (int)location.y, bearing, target.getCreep());
  }

  public String getName() {
    return "Cruise Launcher";
  }

}
