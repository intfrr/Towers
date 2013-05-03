package lineup.model.implementations.bunkers;

import java.awt.Graphics;
import java.awt.Image;

import lineup.model.Bunker;

/**
 * Large bunker with 20 size.
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
    sprite = loadSprite(BunkerType.L);
  }

  public void render(Graphics g) {
    g.drawImage(sprite, (int)getLocation().x, (int)getLocation().y, null);
  }

  public String getName() {
    return "Large Bunker";
  }

  public String getDescription() {
    return "A large bunker";
  }

}
