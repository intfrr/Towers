package lineup.model.implementations.bunkers;

import java.awt.Graphics;
import java.awt.Image;

import lineup.model.Bunker;
import lineup.model.implementations.arms.Cannon;
import lineup.model.implementations.trackers.BasicTracker;

/**
 * Large bunker with 20 size.
 * Contains a Cannon and BasicTracker.
 * @author Neil
 *
 */
public class LargeBunker extends Bunker {

  private Image sprite;
  
  /**
   * Constructor.
   */
  public LargeBunker() {
    super(20, 200);
    setArms(new Cannon(this));
    setTracking(new BasicTracker());
    sprite = loadSprite(BunkerType.L);
  }

  public void render(Graphics g) {
    g.drawImage(sprite, (int)getLocation().x, (int)getLocation().y, null);
  }

  @Override
  public String getName() {
    return "Large Bunker";
  }

}
