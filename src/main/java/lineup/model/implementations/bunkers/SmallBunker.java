package lineup.model.implementations.bunkers;

import java.awt.Graphics;
import java.awt.Image;

import lineup.model.Bunker;

/**
 * Small bunker with 10 size.
 * @author Neil
 *
 */
public class SmallBunker extends Bunker {

  private Image sprite;
  
  /**
   * Constructor.
   */
  public SmallBunker() {
    super(10, 50);
    sprite = loadSprite(BunkerType.S);
  }

  public void render(Graphics g) {
    g.drawImage(sprite, (int)getLocation().x, (int)getLocation().y, null);
  }

  public String getName() {
    return "Small Bunker";
  }

  public String getDescription() {
    return "A small bunker";
  }

}
