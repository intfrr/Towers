package lineup.model.implementations.creeps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import lineup.model.Creep;
import lineup.model.Location;

public class YellowCreep extends Creep {

  /**
   * Constructor.
   * @param route
   */
  public YellowCreep(List<Location> route) {
    super("Yellow Creep", 20, 3, route);
  }

  private int size = 4;
  private Color color = Color.YELLOW;
  
  @Override
  public void renderCreep(Graphics g) {
    g.setColor(color);
    g.fillOval((int)getLocation().x, (int)getLocation().y, size, size);
  }

  @Override
  public String toString() {
    return getName() + " h:" + getHealth() + " at " + getLocation();
  }
  
  @Override
  public Creep copy() {
    YellowCreep copy = new YellowCreep(new LinkedList<Location>(getRoute()));
    //System.out.println("Spawning " + copy + " with route " + copy.getRoute());

    return copy;
  }

  @Override
  public int getValue() {
    return 4;
  }

  @Override
  public int getWidth() {
    return size;
  }

  @Override
  public int getHeight() {
    return size;
  }

}
