package lineup.model.implementations.arms.heavy;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.LargeShell;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.util.math.Vector2D;

/**
 * Cannon with 5 sec reload that fires Shells directly
 * at the target with no leading.
 * @author Neil
 */
public class HeavyCannon extends SingleShotGun {

  private static final int reload = 5000;
  
  /**
   * Constructor.
   */
  public HeavyCannon(Bunker bunker) {
    super(bunker, reload, 10, 6);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    return new LargeShell(getOwner(), (int)location.x, (int)location.y, v.getBearing());
  }

  public String getName() {
    return "Heavy Cannon";
  }

  public int getCost() {
    return 60;
  }

  public String getDescription() {
    return "Large cannon that fires shells in a straight line";
  }

  @Override
  public double getDPS() {
    LargeShell s = new LargeShell(null, 0, 0, 0);
    double d = s.getDamage();
    return d * 1000/reload;
  }

}
