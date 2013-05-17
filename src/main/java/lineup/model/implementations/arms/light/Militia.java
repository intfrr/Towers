package lineup.model.implementations.arms.light;

import java.util.ArrayList;
import java.util.List;

import lineup.model.Arms;
import lineup.model.Bunker;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.model.Target;

/**
 * Militia!
 * @author Neil
 */
public class Militia extends Arms {
  
  private Pistol pistol;
  private AK47 ak47;
  private Shotgun shotgun;
  private GrenadeThrower grenade;
  private MolotovThrower molotov;
  
  /**
   * Constructor
   * @param bunker
   */
  public Militia(Bunker bunker) {
    super(8, 2);
    this.pistol = new Pistol(bunker);
    this.ak47 = new AK47(bunker);
    this.shotgun = new Shotgun(bunker);
    this.grenade = new GrenadeThrower(bunker);
    this.molotov = new MolotovThrower(bunker);
  }
  

  @Override
  public List<Projectile> fire(Location location, List<Target> targets) {
    List<Projectile> shots = new ArrayList<Projectile>();
    
    if (targets.size() > 0) {
      if (pistol.isReady()) {
        shots.addAll(pistol.fire(location, targets));
      }
      if (ak47.isReady()) {
        shots.addAll(ak47.fire(location, targets));
      }
      if (shotgun.isReady()) {
        shots.addAll(shotgun.fire(location, targets));
      }
      if (grenade.isReady()) {
        shots.addAll(grenade.fire(location, targets));
      }
      if (molotov.isReady()) {
        shots.addAll(molotov.fire(location, targets));
      }
    }
    
    return shots;
  }

  @Override
  public boolean isReady() {
    return pistol.isReady() || ak47.isReady() || shotgun.isReady() ||
           grenade.isReady() || molotov.isReady();
  }

  @Override
  public void cooldown(int elapsed) {
    pistol.cooldown(elapsed);
    ak47.cooldown(elapsed);
    shotgun.cooldown(elapsed);
    grenade.cooldown(elapsed);
    molotov.cooldown(elapsed);
  }

  public String getName() {
    return "Militia";
  }

  public int getCost() {
    return 100;
  }

  public String getDescription() {
    return "Ragged group of folks armed with whatever they can find.";
  }

  @Override
  public double getDPS() {
    return pistol.getDPS() + ak47.getDPS() + shotgun.getDPS() + grenade.getDPS() + molotov.getDPS();
  }
}
