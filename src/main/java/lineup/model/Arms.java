package lineup.model;

import java.util.List;

public interface Arms extends Purchasable {

  public List<Projectile> fire(Location location, List<Target> targets);
  public boolean isReady();
  public void cooldown(int elapsed);
  
}
