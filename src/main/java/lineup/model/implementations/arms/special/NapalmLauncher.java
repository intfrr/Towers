package lineup.model.implementations.arms.special;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.NapalmShell;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.model.implementations.blasts.NapalmBlast;
import lineup.util.math.Vector2D;

/**
 * Launches napalm canisters.
 * @author Neil
 */
public class NapalmLauncher extends SingleShotGun {

  /**
   * Constructor.
   */
  public NapalmLauncher(Bunker bunker) {
    super(bunker, 4000, 15, 8);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    return new NapalmShell(getOwner(), (int)location.x, (int)location.y, v.getBearing());
  }

  public String getName() {
    return "Napalm launcher";
  }

  public int getCost() {
    return 100;
  }

  public String getDescription() {
    return "Napalm launcher, woo!";
  }

  @Override
  public double getDPS() {
    return new NapalmBlast(0, 0, null).calcDamage(1000);
  }

}
