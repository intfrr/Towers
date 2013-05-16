package lineup.model.implementations.blasts;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;

import lineup.model.Blast;
import lineup.model.Bunker;

public abstract class AnimatedBlast extends Blast {
  
  private int frameCycleTime = 1000;
  private int time;
  private int spriteIndex;
  private int numFrames;
  
  
  public AnimatedBlast(Shape shape, Bunker owner, double damage, int ttl) {
    super(shape, owner, damage, ttl);
    this.time = frameCycleTime;
    this.spriteIndex = 1;
    this.numFrames = 1;
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
  public void render(Graphics2D g2) {
    int x = getShape().getBounds().x;
    int y = getShape().getBounds().y;
    g2.drawImage(getSprite(spriteIndex), x, y, null);
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
