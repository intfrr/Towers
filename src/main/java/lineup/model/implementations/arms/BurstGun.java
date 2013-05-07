package lineup.model.implementations.arms;

import java.util.ArrayList;
import java.util.List;

import lineup.model.Arms;
import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;

/**
 * Generic projectile firing Arms that fires in bursts.
 * @author Neil
 */
public abstract class BurstGun implements Arms {

  private int reload;
  private int cooldown;
  
  private int burstSize;
  private int burstRemaining;
  private int burstInterval;
  private int burstCooldown;
  
  private Bunker owner;
  
  public BurstGun(Bunker owner, int reload, int burstSize, int burstInterval) {
    this.owner = owner;
    this.reload = reload;
    this.burstSize = burstSize;
    this.burstRemaining = burstSize;
    this.burstInterval = burstInterval;
    this.burstCooldown = burstInterval;
  }
  
  public List<Projectile> fire(Location location, List<Target> targets) {
    List<Projectile> shots = new ArrayList<Projectile>();
    if (targets.size() > 0) {
      
      if (burstRemaining > 0) {
        burstRemaining--;
        burstCooldown = burstInterval;
        shots.add(createProjectile(location, targets.get(0)));
      }
      
      if (burstRemaining == 0) {
        cooldown = reload;
        burstRemaining = burstSize;
        burstCooldown = burstInterval;
      }
    }
    return shots;
  }

  public abstract Projectile createProjectile(Location location, Target target);

  public boolean isReady() {
    return cooldown == 0 && burstCooldown == 0;
  }

  public void cooldown(int elapsed) {
    if (cooldown > 0) {
      if (cooldown > elapsed) {
        cooldown = cooldown - elapsed;
      } else {
        cooldown = 0;
      }
    }
    
    if (burstCooldown > 0) {
      if (burstCooldown > elapsed) {
        burstCooldown = burstCooldown - elapsed;
      } else {
        burstCooldown = 0;
      }
    }
  }
  
  public Bunker getOwner() {
    return owner;
  }
  
  public void setOwner(Bunker bunker) {
    this.owner = bunker;
  }
  
}
