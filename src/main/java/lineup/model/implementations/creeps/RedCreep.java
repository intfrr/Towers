package lineup.model.implementations.creeps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import lineup.model.Creep;
import lineup.model.Location;

public class RedCreep extends Creep {

  /**
   * Constructor.
   * @param route
   */
  public RedCreep(List<Location> route) {
    super("Red Creep", 30, 12, route);
  }

  private int size = 8;
  private Color color = Color.RED;
  
  @Override
  public void renderCreep(Graphics g) {
    g.setColor(color);
    g.fillOval((int)getLocation().x - getWidth()/2, (int)getLocation().y - getHeight()/2, size, size);
  }

  @Override
  public String toString() {
    return getName() + " h:" + getHealth() + " at " + getLocation();
  }
  
  @Override
  public Creep copy() {
    return new RedCreep(new LinkedList<Location>(getRoute()));
  }

  @Override
  public int getValue() {
    return 10;
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
