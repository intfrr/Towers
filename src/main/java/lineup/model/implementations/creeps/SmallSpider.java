package lineup.model.implementations.creeps;

import java.util.LinkedList;
import java.util.List;

import lineup.model.Creep;
import lineup.model.Location;

public class SmallSpider extends AnimatedCreep {

  /**
   * Constructor.
   * @param route
   */
  public SmallSpider(List<Location> route) {
    super("Small Spider", 10, 5, route, true);
  }

  @Override
  protected int getFrameCycleTime() {
    return 300;
  }

  @Override
  protected String getSpriteName() {
    return "smallspider";
  }

  @Override
  public Creep copy() {
    return new SmallSpider(new LinkedList<Location>(getRoute()));
  }

  @Override
  public int getValue() {
    return 4;
  }

  @Override
  public int getWidth() {
    return 8;
  }

  @Override
  public int getHeight() {
    return 8;
  }

}
