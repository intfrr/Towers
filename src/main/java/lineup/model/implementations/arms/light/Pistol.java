package lineup.model.implementations.arms.light;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.SmallBullet;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.util.math.Vector2D;

/**
 * Pistol, used by militia
 * @author Neil
 */
public class Pistol extends SingleShotGun {

  /**
   * Constructor.
   */
  public Pistol(Bunker bunker) {
    super(bunker, 2000, 6, 3);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    return new SmallBullet(getOwner(), (int)location.x, (int)location.y, v.getBearing());
  }

  public String getName() {
    return "Pistol";
  }

  public int getCost() {
    return 0;
  }

  public String getDescription() {
    return "Pistol";
  }

  @Override
  public double getDPS() {
    // dps = bullets/sec * damage/bullet
    double rate = 1000.0/2000.0;
    double d = new SmallBullet(null, 0, 0, 0).getDamage();
    return rate * d;
  }

}
