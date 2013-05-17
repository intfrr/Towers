package lineup.model.implementations.arms.light;

import java.util.ArrayList;
import java.util.List;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.arms.MultiShotGun;
import lineup.model.implementations.projectiles.SmallBullet;
import lineup.util.math.Vector2D;

/**
 * Shotgun, used by Militia
 * @author Neil
 */
public class Shotgun extends MultiShotGun {
  
  /**
   * Constructor.
   */
  public Shotgun(Bunker bunker) {
    super(bunker, 8500, 1, 1);
  }

  @Override
  public List<Projectile> createProjectile(Location location, Target target) {
    
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());

    List<Projectile> shots = new ArrayList<Projectile>(3);
    shots.add(new SmallBullet(getOwner(), (int)location.x, (int)location.y, v.getBearing() - Math.PI/15.0));
    shots.add(new SmallBullet(getOwner(), (int)location.x, (int)location.y, v.getBearing()));
    shots.add(new SmallBullet(getOwner(), (int)location.x, (int)location.y, v.getBearing() + Math.PI/15.0));
    return shots;
  }

  public String getName() {
    return "Shotgun";
  }

  public int getCost() {
    return 0;
  }

  public String getDescription() {
    return "Shotgun";
  }

  @Override
  public double getDPS() {
    double rate = 3.0 * 1000.0/8500.0;
    double d = new SmallBullet(null, 0, 0, 0).getDamage();
    return rate * d;
  }

}
