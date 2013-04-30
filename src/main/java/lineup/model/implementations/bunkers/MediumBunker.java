package lineup.model.implementations.bunkers;

import java.awt.Graphics;
import java.awt.Image;

import lineup.model.Bunker;

/**
 * Medium bunker with 15 size.
 * Contains a Cannon and BasicTracker.
 * @author Neil
 *
 */
public class MediumBunker extends Bunker {

  private Image sprite;
  
  /**
   * Constructor.
   */
  public MediumBunker() {
    super(15, 100);
    sprite = loadSprite(BunkerType.M);
  }

  public void render(Graphics g) {
    g.drawImage(sprite, (int)getLocation().x, (int)getLocation().y, null);
  }

  @Override
  public String getName() {
    return "Medium Bunker";
  }

}
