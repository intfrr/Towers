package lineup.model.implementations.arms.heavy;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.SmallShell;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.util.math.Vector2D;

/**
 * Fast firing AutoCannon that fires directly
 * at the target with no leading.
 * @author Neil
 */
public class AutoCannon extends SingleShotGun {

  private static int reload = 1200;
  
  /**
   * Constructor.
   */
  public AutoCannon(Bunker bunker) {
    super(bunker, reload, 4, 3);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    return new SmallShell(getOwner(), (int)location.x, (int)location.y, v.getBearing());
  }

  public String getName() {
    return "AutoCannon";
  }

  public int getCost() {
    return 30;
  }

  public String getDescription() {
    return "AutoCannon that fires shells in a straight line";
  }

  @Override
  public double getDPS() {
    SmallShell s = new SmallShell(null, 0, 0, 0);
    double d = s.getDamage();
    return d * 1000/reload;
  }

}
