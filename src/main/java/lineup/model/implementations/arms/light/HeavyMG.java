package lineup.model.implementations.arms.light;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.arms.BurstGun;
import lineup.model.implementations.projectiles.SmallBullet;
import lineup.util.math.Vector2D;

/**
 * Heavy machine gun that fires bursts.
 * @author Neil
 */
public class HeavyMG extends BurstGun {

  /**
   * Constructor.
   */
  public HeavyMG(Bunker bunker) {
    super(bunker, 4000, 8, 140);
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
    return "Heavy Machine Gun";
  }

  public int getCost() {
    return 60;
  }

  public String getDescription() {
    return "Heavy machine gun that fires bursts. Is able to lead targets.";
  }

}
