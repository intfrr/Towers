package lineup.model.implementations.arms.special;

import java.util.ArrayList;
import java.util.List;

import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;
import lineup.model.implementations.arms.MultiShotGun;
import lineup.model.implementations.projectiles.SmallMissile;
import lineup.util.math.Vector2D;

/**
 * Missile launcher that fires several light missiles.
 * @author Neil
 */
public class MultipleLauncher extends MultiShotGun {
  
  /**
   * Constructor.
   */
  public MultipleLauncher(Bunker bunker) {
    super(bunker, 5000);
  }

  @Override
  public List<Projectile> createProjectile(Location location, Target target) {
    
    Vector2D v = new Vector2D(location, target.getCreep().getCentreLocation());

    List<Projectile> shots = new ArrayList<Projectile>(3);
    shots.add(new SmallMissile(getOwner(), (int)location.x, (int)location.y, v.getBearing() - Math.PI/4, target.getCreep()));
    shots.add(new SmallMissile(getOwner(), (int)location.x, (int)location.y, v.getBearing(), target.getCreep()));
    shots.add(new SmallMissile(getOwner(), (int)location.x, (int)location.y, v.getBearing() + Math.PI/4, target.getCreep()));
    return shots;
  }

  public String getName() {
    return "MultipleLauncher";
  }

  public int getCost() {
    return 80;
  }

  public String getDescription() {
    return "Missile launcher that fires several light missiles with limited homing";
  }

}
