package lineup.model.implementations.creeps;

import java.util.LinkedList;
import java.util.List;

import lineup.model.Creep;
import lineup.model.Location;

public class Mothership extends AnimatedCreep {

  /**
   * Constructor.
   * @param route
   */
  public Mothership(List<Location> route) {
    super("Mothership", 6, 500, route, false);
  }

  @Override
  protected int getFrameCycleTime() {
    return 100;
  }

  @Override
  protected String getSpriteName() {
    return "ms";
  }

  @Override
  public Creep copy() {
    return new Mothership(new LinkedList<Location>(getRoute()));
  }

  @Override
  public int getValue() {
    return 150;
  }

  @Override
  public int getWidth() {
    return 18;
  }

  @Override
  public int getHeight() {
    return 10;
  }

}
