package lineup.model.implementations.blasts;

import java.awt.Image;
import java.awt.geom.Ellipse2D;

import lineup.model.Bunker;
import lineup.util.ui.ImageLoader;

public class NapalmBlast extends AnimatedBlast {
  
  private static final double size = 30.0;
  
  private static Image[] sprites = new Image[3];
  
  static {
    sprites[0] = ImageLoader.loadSprite("napalm_0.png");
    sprites[1] = ImageLoader.loadSprite("napalm_1.png");
    sprites[2] = ImageLoader.loadSprite("napalm_2.png");
  }
  
  /**
   * Constructor.
   * @param x
   * @param y
   * @param owner
   */
  public NapalmBlast(double x, double y, Bunker owner) {
    super(new Ellipse2D.Double(x-size/2, y-size/2, size, size), owner, 2.0, 4000);
    setPersistant(true);
    //setDamage(10);
    //setTtl(4000);
    
    //Animation details
    setFrameCycleTime(200);
    setNumFrames(sprites.length);
  }

  @Override
  protected Image getSprite(int frame) {
    return sprites[frame - 1];
  }
  
}
