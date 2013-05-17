package lineup.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import lineup.exception.DoesntFitException;
import lineup.model.implementations.bunkers.LargeBunker;
import lineup.model.implementations.bunkers.MediumBunker;
import lineup.model.implementations.bunkers.SmallBunker;
import lineup.util.ui.ImageLoader;
import lineup.world.Renderable;
import lineup.world.Updateable;
import lineup.world.World;

/**
 * Base bunker class.
 * @author Neil
 *
 */
public abstract class Bunker extends Storage implements Renderable, Updateable, Purchasable {
  
  public enum BunkerType {S, M, L}
  
  private Location location;
  private TrackingSystem tracking;
  private Arms arms;
  private boolean selected;
  private int kills;
  private int cost;
  private int power;
  
  /**
   * Constructor.
   * @param size
   * @param power
   * @param cost
   */
  public Bunker(int size, int power, int cost) {
    super(size);
    this.power = power;
    this.cost = cost;
  }
  
  public void update(int elapsed) {
    if (tracking != null && arms != null) {
      arms.cooldown(elapsed);
      List<Target> targets = tracking.getTargets(getCentreLocation(), World.getInstance().getCreeps());
      if (arms.isReady()) {
        List<Projectile> shots = arms.fire(getCentreLocation(), targets);
        World.getInstance().getProjectiles().addAll(shots);
      }
    }
  }
  
  public void borderRender(Graphics g) {
    if (selected) {
      g.setColor(Color.CYAN);
      g.drawRect((int)getLocation().x - 1, (int)getLocation().y - 1, getSize() + 1, getSize() + 1);
      
      if (tracking != null) {
        g.drawOval((int)location.x - tracking.getRange() + getSize()/2 - 1, (int)location.y - tracking.getRange() + getSize()/2 - 1, tracking.getRange()*2, tracking.getRange()*2);
      }
    }
    render(g);
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public void setLocation(int x, int y) {
    setLocation(new Location(x, y));
  }

  public TrackingSystem getTracking() {
    return tracking;
  }

  public void setTracking(TrackingSystem tracking) throws DoesntFitException {
    remove(this.tracking);
    try {
      add(tracking);
    } catch (DoesntFitException ex) {
      add(this.tracking);
    }
    this.tracking = tracking;
  }

  public Arms getArms() {
    return arms;
  }

  public void setArms(Arms arms) throws DoesntFitException {
    remove(this.arms);
    try {
      add(arms);
    } catch (DoesntFitException ex) {
      add(this.arms);
    }
    this.arms = arms;
  }

  public Rectangle getBoundingRect() {
    return new Rectangle((int)location.x, (int)location.y, getSize(), getSize());
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }
  
  public boolean isSelected() {
    return selected;
  }

  public void incrementKills() {
    kills++;
  }

  public int getKills() {
    return kills;
  }
  
  public int getCost() {
    return cost;
  }
  
  public Location getCentreLocation() {
    return new Location(location.x + getSize()/2, location.y + getSize()/2);
  }
  
  protected Image loadSprite(BunkerType type) {
    return ImageLoader.loadSprite("bunker_" + type.name() + ".png");
  }

  public static Bunker create(BunkerType type, int x, int y) {
    Bunker bunker = null;
    switch (type) {
      case S:
        bunker = new SmallBunker();
        break;
      case M:
        bunker = new MediumBunker();
        break;
      case L:
        bunker = new LargeBunker();
        break;
      default:
        throw new RuntimeException("Unknown bunker type " + type); 
    }
    bunker.setLocation(x, y);
    return bunker;
  }

  public int getSellValue() {
    int val = cost;
    if (arms != null) {
      val += arms.getCost();
    }
    if (tracking != null) {
      val += tracking.getCost();
    }
    return val/2;
  }

  public int getFreePower() {
    int free = power;
    if (arms != null) {
      free -= arms.getPower();
    }
    if (tracking != null) {
      free -= tracking.getPower();
    }
    return free;
  }

  public boolean canUpgrade(TrackingSystem t) {
    int pUsed = 0;
    int sUsed = 0;
    if (arms != null) {
      pUsed += arms.getPower();
      sUsed += arms.getSize();
    }
    return pUsed + t.getPower() <= power &&
           sUsed + t.getSize() <= getSize();
  }
  
  public boolean canUpgrade(Arms a) {
    int pUsed = 0;
    int sUsed = 0;
    if (tracking != null) {
      pUsed += tracking.getPower();
      sUsed += tracking.getSize();
    }
    return pUsed + a.getPower() <= power &&
           sUsed + a.getSize() <= getSize();
  }

}
