package lineup.model.implementations.creeps;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import lineup.model.Creep;
import lineup.model.Location;
import lineup.util.ui.ImageLoader;

public class SmallSpider extends AnimatedCreep {

  private static Image[] sprites = new Image[2];
  
  static {
    sprites[0] = ImageLoader.loadSprite("smallspider_0.png");
    sprites[1] = ImageLoader.loadSprite("smallspider_1.png");
  }
  
  /**
   * Constructor.
   * @param route
   */
  public SmallSpider(List<Location> route) {
    super("Small Spider", 10, 5, route, true);
    
    //Animation details
    setFrameCycleTime(300);
    setNumFrames(sprites.length);
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


  @Override
  protected Image getSprite(int frame) {
    return sprites[frame - 1];
  }

}
