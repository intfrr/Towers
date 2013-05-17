package lineup.model.implementations.arms.special;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.projectiles.LightRocket;
import lineup.model.implementations.arms.SingleShotGun;
import lineup.util.math.Vector2D;

/**
 * Light rocket launcher.
 * @author Neil
 */
public class LightRocketLauncher extends SingleShotGun {

  /**
   * Constructor.
   */
  public LightRocketLauncher(Bunker bunker) {
    super(bunker, 5000, 4, 3);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    Vector2D cv = target.getCreep().getVector();
    
    Projectile p = new LightRocket(getOwner(), (int)location.x, (int)location.y, v.getBearing());
    p.getVector().add(cv);

    return p;
  }

  public String getName() {
    return "Rocket Launcher";
  }

  public int getCost() {
    return 40;
  }

  public String getDescription() {
    return "Rocket launcher with a medium rate of fire. Fires small rockets leading the target.";
  }

  @Override
  public double getDPS() {
    double d = new LightRocket(null, 0, 0, 0).getDamage();
    return d * 1000.0/5000.0;
  }

}
