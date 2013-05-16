package lineup.model.implementations.creeps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import lineup.model.Creep;
import lineup.model.Location;

public class BlueCreep extends Creep {

  private int height = 6;
  private int width = 6;
  private Color color = Color.BLUE;
  
  /**
   * Constructor.
   * @param route
   */
  public BlueCreep(List<Location> route) {
    super("Blue Creep", 20, 6, route);
  }
  
  @Override
  public void renderCreep(Graphics g) {
    g.setColor(color);
    g.fillOval((int)getLocation().x - getWidth()/2, (int)getLocation().y - getHeight()/2, width, height);
  }

  @Override
  public String toString() {
    return getName() + " h:" + getHealth() + " at " + getLocation();
  }
  
  @Override
  public Creep copy() {
    return new BlueCreep(new LinkedList<Location>(getRoute()));
  }

  @Override
  public int getValue() {
    return 5;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

}
