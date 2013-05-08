package lineup.model.implementations.arms.light;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.arms.BurstGun;
import lineup.model.implementations.projectiles.SmallBullet;
import lineup.util.math.Vector2D;

/**
 * Light machine gun that fires short bursts.
 * @author Neil
 */
public class LightMG extends BurstGun {

  /**
   * Constructor.
   */
  public LightMG(Bunker bunker) {
    super(bunker, 3000, 4, 200);
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
    return "Light Machine Gun";
  }

  public int getCost() {
    return 30;
  }

  public String getDescription() {
    return "Light machine gun that fires short bursts. Is able to lead targets.";
  }

}
