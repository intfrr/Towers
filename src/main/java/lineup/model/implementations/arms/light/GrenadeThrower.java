package lineup.model.implementations.arms.light;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.Grenade;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.util.math.Vector2D;

/**
 * Grenade, used by militia
 * @author Neil
 */
public class GrenadeThrower extends SingleShotGun {

  /**
   * Constructor.
   */
  public GrenadeThrower(Bunker bunker) {
    super(bunker, 10000, 1, 1);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    
    double dist = location.asPoint().distance(target.getCreep().getCentreLocation().asPoint());
    int ttl = (int)(1000.0*dist/Grenade.VELOCITY);
    return new Grenade(getOwner(), (int)location.x, (int)location.y, v.getBearing(), ttl);
  }

  public String getName() {
    return "Grenade thrower";
  }

  public int getCost() {
    return 0;
  }

  public String getDescription() {
    return "Grenade thrower";
  }

  @Override
  public double getDPS() {
    double rate = 1000.0/10000.0;
    double d = new Grenade(null, 0, 0, 0, 0).getDamage();
    return rate * d;
  }

}
