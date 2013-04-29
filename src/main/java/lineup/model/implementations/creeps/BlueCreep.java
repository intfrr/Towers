package lineup.model.implementations.creeps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import lineup.model.Creep;
import lineup.model.Location;

public class BlueCreep extends Creep {

  /**
   * Constructor.
   * @param route
   */
  public BlueCreep(List<Location> route) {
    super("Blue Creep", 0.2, 10, route);
  }

  private int size = 6;
  private Color color = Color.BLUE;
  
  public void render(JPanel view) {
    Graphics g = view.getGraphics();
    g.setColor(color);
    g.fillOval((int)getLocation().x, (int)getLocation().y, size, size);
  }

  @Override
  public String toString() {
    return getName() + " h:" + getHealth() + " at " + getLocation();
  }
  
  @Override
  public Creep copy() {
    BlueCreep copy = new BlueCreep(new LinkedList<Location>(getRoute()));
    //System.out.println("Spawning " + copy + " with route " + copy.getRoute());

    return copy;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public int getValue() {
    return 5;
  }

}