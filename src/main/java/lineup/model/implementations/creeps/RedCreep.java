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
    super("Red Creep", 0.3, 20, route);
  }

  private int size = 8;
  private Color color = Color.RED;
  
  public void render(Graphics g) {
    g.setColor(color);
    g.fillOval((int)getLocation().x, (int)getLocation().y, size, size);
  }

  @Override
  public String toString() {
    return getName() + " h:" + getHealth() + " at " + getLocation();
  }
  
  @Override
  public Creep copy() {
    RedCreep copy = new RedCreep(new LinkedList<Location>(getRoute()));
    //System.out.println("Spawning " + copy + " with route " + copy.getRoute());

    return copy;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public int getValue() {
    return 10;
  }

}
