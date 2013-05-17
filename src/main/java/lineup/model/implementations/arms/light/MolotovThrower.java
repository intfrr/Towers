package lineup.model.implementations.arms.light;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.Molotov;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.model.implementations.blasts.MolotovBlast;
import lineup.util.math.Vector2D;

/**
 * Throws molotovs.
 * @author Neil
 */
public class MolotovThrower extends SingleShotGun {

  /**
   * Constructor.
   */
  public MolotovThrower(Bunker bunker) {
    super(bunker, 12000, 1, 1);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    
    double dist = location.asPoint().distance(target.getCreep().getCentreLocation().asPoint());
    int ttl = (int)(1000.0*dist/Molotov.VELOCITY);
    return new Molotov(getOwner(), (int)location.x, (int)location.y, v.getBearing(), ttl);
  }

  public String getName() {
    return "Napalm launcher";
  }

  public int getCost() {
    return 0;
  }

  public String getDescription() {
    return "Molotov thrower";
  }

  @Override
  public double getDPS() {
    return new MolotovBlast(0, 0, null).calcDamage(1000);
  }

}
