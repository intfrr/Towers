package lineup.model.implementations.arms;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.CruiseMissile;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.util.math.Vector2D;

/**
 * Missile launcher with 8 sec reload that fires cruise
 * missiles.
 * @author Neil
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
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    return new CruiseMissile(getOwner(), (int)location.x, (int)location.y, v.getBearing(), target.getCreep());
  }

  public String getName() {
    return "Cruise Launcher";
  }

  public int getCost() {
    return 80;
  }

  public String getDescription() {
    return "Missile launcher with a slow rate of fire. Fires powerful seeking cruise missiles";
  }

}
