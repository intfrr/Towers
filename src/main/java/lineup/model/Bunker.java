package lineup.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import lineup.world.Renderable;
import lineup.world.Updateable;
import lineup.world.World;

/**
 * Base bunker class.
 * @author 38183Ne
 *
 */
public abstract class Bunker extends Storage implements Renderable, Updateable {
  
  private Location location;
  private TrackingSystem tracking;
  private Arms arms;
  private boolean selected;
  private int kills;
  
  /**
   * Constructor.
   * @param size
   */
  public Bunker(int size) {
    super(size);
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

  public void setTracking(TrackingSystem tracking) {
    this.tracking = tracking;
  }

  public Arms getArms() {
    return arms;
  }

  public void setArms(Arms arms) {
    this.arms = arms;
  }

  public Rectangle getBoundingRect() {
    return new Rectangle((int)location.x, (int)location.y, getSize(), getSize());
  }
  
  public abstract String getName();

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
  
  public Location getCentreLocation() {
    return new Location(location.x + getSize()/2, location.y + getSize()/2);
  }

}
