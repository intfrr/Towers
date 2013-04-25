package lineup.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Level {

  private Deque<Wave> waves = new LinkedList<Wave>();
  private List<Location> waypoints = new ArrayList<Location>();
  private Image background;
  
  private Location base;
  
  public Image getBackground() {
    return background;
  }

  public void setBackground(Image background) {
    this.background = background;
  }

  public Deque<Wave> getWaves() {
    return waves;
  }

  public void setWaves(Deque<Wave> waves) {
    this.waves = waves;
  }

  public List<Location> getWaypoints() {
    return waypoints;
  }

  public void setWaypoints(List<Location> waypoints) {
    this.waypoints = waypoints;
  }

  public Location getBase() {
    return base;
  }

  public void setBase(Location base) {
    this.base = base;
  }
  
}
