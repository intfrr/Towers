package lineup.model.implementations.arms;

import java.util.ArrayList;
import java.util.List;

import lineup.model.Arms;
import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;

/**
 * Generic single projectile firing Arms.
 * @author Neil
 */
public abstract class SingleShotGun implements Arms {

  private int reload;
  private int cooldown;
  private Bunker owner;
  
  public SingleShotGun(Bunker owner, int reload) {
    this.owner = owner;
    this.reload = reload;
    this.cooldown = 0;
  }
  
  public List<Projectile> fire(Location location, List<Target> targets) {
    List<Projectile> shots = new ArrayList<Projectile>();
    if (targets.size() > 0) {
      //System.out.println("Firing");
      cooldown = reload;
      shots.add(createProjectile(location, targets.get(0)));
    }
    return shots;
  }

  public abstract Projectile createProjectile(Location location, Target target);

  public boolean isReady() {
    return cooldown == 0;
  }

  public void cooldown(int elapsed) {
    if (cooldown > 0) {
      if (cooldown > elapsed) {
        cooldown = cooldown - elapsed;
      } else {
        cooldown = 0;
      }
    }
  }
  
  public Bunker getOwner() {
    return owner;
  }
  
}
