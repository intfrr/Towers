package lineup.model.implementations.arms;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.Shell;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.util.math.Vector2D;

/**
 * Cannon with 5 sec reload that fires Shells directly
 * at the target with no leading.
 * @author Neil
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
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    return new Shell(getOwner(), (int)location.x, (int)location.y, v.getBearing());
  }

  public String getName() {
    return "Cannon";
  }

  public int getCost() {
    return 30;
  }

  public String getDescription() {
    return "Basic cannon that fires shells in a straight line";
  }

}
