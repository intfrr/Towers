package lineup.model.implementations.creeps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.List;

import lineup.model.Creep;
import lineup.model.Location;

public abstract class AnimatedCreep extends Creep {
  
  private AffineTransform identity = new AffineTransform();
  private boolean imageRotates;
  
  private int frameCycleTime = 1000;
  private int time;
  private int spriteIndex;
  private int numFrames;
  
  /**
   * Constructor.
   * @param name
   * @param velocity
   * @param health
   * @param route
   */
  public AnimatedCreep(String name, double velocity, int health, List<Location> route, boolean imageRotates) {
    super(name, velocity, health, route);
    this.time = frameCycleTime;
    this.spriteIndex = 1;
    this.numFrames = 1;
    this.imageRotates = imageRotates;
  }


  @Override
  public void update(int elapsed) {
    super.update(elapsed);
    time -= elapsed;
    if (time <= 0) {
      time = frameCycleTime;
      spriteIndex++;
      
      if (spriteIndex > numFrames) {
        spriteIndex = 1;
      }
    }
  }


  @Override
  protected void renderCreep(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    AffineTransform xform = new AffineTransform();
    xform.setTransform(identity);
    xform.translate(getVector().getX() - getWidth()/2, getVector().getY() - getHeight()/2);
    
    if (imageRotates) {  
      xform.rotate(getVector().getBearing(), getWidth()/2, getHeight()/2);
    }  
    
    g2.drawImage(getSprite(spriteIndex), xform, null);
  }

  
  protected abstract Image getSprite(int frame);


  public void setFrameCycleTime(int frameCycleTime) {
    this.frameCycleTime = frameCycleTime;
    this.time = frameCycleTime;
  }


  public void setNumFrames(int numFrames) {
    this.numFrames = numFrames;
  }

}
