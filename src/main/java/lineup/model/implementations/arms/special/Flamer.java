package lineup.model.implementations.arms.special;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.FlameBall;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.util.math.Vector2D;

/**
 * Flame thrower.
 * @author Neil
 */
public class Flamer extends SingleShotGun {

  /**
   * Constructor.
   */
  public Flamer(Bunker bunker) {
    super(bunker, 50);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    Vector2D cv = target.getCreep().getVector();
    
    Projectile p = new FlameBall(getOwner(), (int)location.x, (int)location.y, v.getBearing());
    p.getVector().add(cv);

    return p;
  }

  public String getName() {
    return "Flame thrower";
  }

  public int getCost() {
    return 80;
  }

  public String getDescription() {
    return "Short range flame thrower.";
  }

}
