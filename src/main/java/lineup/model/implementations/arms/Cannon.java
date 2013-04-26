package lineup.model.implementations.arms;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.Shell;
import lineup.model.implementations.arms.SingleShotGun;

/**
 * Cannon with 5 sec reload that fires Shells directly
 * at the target with no leading.
 * @author 38183Ne
 */
public class Cannon extends SingleShotGun {

  /**
   * Constructor.
   */
  public Cannon(Bunker bunker) {
    super(bunker, 1000);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    double dx = target.getCreep().getCentreLocation().x - location.x;
    double dy = target.getCreep().getCentreLocation().y - location.y;
    double bearing = Math.atan2(dy, dx);
    
    return new Shell(getOwner(), (int)location.x, (int)location.y, bearing);
  }

  public String getName() {
    return "Cannon";
  }

}
