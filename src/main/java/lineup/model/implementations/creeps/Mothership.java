package lineup.model.implementations.creeps;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import lineup.model.Creep;
import lineup.model.Location;
import lineup.util.ui.ImageLoader;

public class Mothership extends AnimatedCreep {

  private static Image[] sprites = new Image[11];
  
  static {
    sprites[0] = ImageLoader.loadSprite("ms_0.png");
    sprites[1] = ImageLoader.loadSprite("ms_1.png");
    sprites[2] = ImageLoader.loadSprite("ms_2.png");
    sprites[3] = ImageLoader.loadSprite("ms_3.png");
    sprites[4] = ImageLoader.loadSprite("ms_4.png");
    sprites[5] = ImageLoader.loadSprite("ms_5.png");
    sprites[6] = ImageLoader.loadSprite("ms_6.png");
    sprites[7] = ImageLoader.loadSprite("ms_7.png");
    sprites[8] = ImageLoader.loadSprite("ms_8.png");
    sprites[9] = ImageLoader.loadSprite("ms_9.png");
    sprites[10] = ImageLoader.loadSprite("ms_10.png");
    
  }
  
  /**
   * Constructor.
   * @param route
   */
  public Mothership(List<Location> route) {
    super("Mothership", 6, 500, route, false);
    
    //Animation details
    setFrameCycleTime(100);
    setNumFrames(sprites.length);
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
  
  @Override
  protected Image getSprite(int frame) {
    return sprites[frame - 1];
  }

}
