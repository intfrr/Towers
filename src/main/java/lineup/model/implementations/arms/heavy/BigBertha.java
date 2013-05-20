package lineup.model.implementations.arms.heavy;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.arms.BurstGun;
import lineup.model.implementations.projectiles.LargeShell;
import lineup.util.math.Vector2D;

/**
 * The BIG one.
 * @author Neil
 */
public class BigBertha extends BurstGun {

  /**
   * Constructor.
   */
  public BigBertha(Bunker bunker) {
    super(bunker, 8000, 2, 200, 15, 9);
  }

  @Override
  public Projectile createProjectile(Location location, Target target) {
    
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());
    return new LargeShell(getOwner(), (int)location.x, (int)location.y, v.getBearing());
  }

  public String getName() {
    return "Big Bertha";
  }

  public int getCost() {
    return 30;
  }

  public String getDescription() {
    return "Large double barrelled cannon.";
  }

  @Override
  public double getDPS() {
    // dps = bullets/sec * damage/bullet
    double rate = (double)(2 * 1000)/(double)(8000 + (2*200));
    double d = new LargeShell(null, 0, 0, 0).getDamage();
    return rate * d;
  }

}
