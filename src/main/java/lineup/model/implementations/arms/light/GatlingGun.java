package lineup.model.implementations.arms.light;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.arms.BurstGun;
import lineup.model.implementations.projectiles.SmallBullet;
import lineup.util.math.Vector2D;

/**
 * Gatling gun that fires long bursts.
 * @author Neil
 */
public class GatlingGun extends BurstGun {

  /**
   * Constructor.
   */
  public GatlingGun(Bunker bunker) {
    super(bunker, 8000, 20, 80);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    Vector2D cv = target.getCreep().getVector();
    
    Projectile p = new SmallBullet(getOwner(), (int)location.x, (int)location.y, v.getBearing());
    p.getVector().add(cv);

    return p;
  }

  public String getName() {
    return "Gatling Gun";
  }

  public int getCost() {
    return 150;
  }

  public String getDescription() {
    return "Gatling gun that fires long bursts. Is able to lead targets.";
  }

}
