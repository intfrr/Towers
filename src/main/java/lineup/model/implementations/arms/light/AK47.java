package lineup.model.implementations.arms.light;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.arms.BurstGun;
import lineup.model.implementations.projectiles.SmallBullet;
import lineup.util.math.Vector2D;

/**
 * Used by militia.
 * @author Neil
 */
public class AK47 extends BurstGun {
  
  /**
   * Constructor.
   */
  public AK47(Bunker bunker) {
    super(bunker, 6500, 3, 300, 1, 1);
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
    return "AK47";
  }

  public int getCost() {
    return 0;
  }

  public String getDescription() {
    return "AK47";
  }

  @Override
  public double getDPS() {
    // dps = bullets/sec * damage/bullet
    double rate = (double)(3 * 1000)/(double)(6500 + (3*300));
    double d = new SmallBullet(null, 0, 0, 0).getDamage();
    return rate * d;
  }

}
