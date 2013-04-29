package lineup.model.implementations.bunkers;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import lineup.model.Bunker;
import lineup.model.implementations.arms.Cannon;
import lineup.model.implementations.trackers.BasicTracker;

/**
 * Medium bunker with 15 size.
 * Contains a Cannon and BasicTracker.
 * @author 38183Ne
 *
 */
public class MediumBunker extends Bunker {

  private Image sprite;
  
  /**
   * Constructor.
   */
  public MediumBunker() {
    super(15, 100);
    setArms(new Cannon(this));
    setTracking(new BasicTracker());
    sprite = loadSprite(BunkerType.M);
  }

  public void render(JPanel view) {
    Graphics g = view.getGraphics();
    g.drawImage(sprite, (int)getLocation().x, (int)getLocation().y, null);
  }

  @Override
  public String getName() {
    return "Medium Bunker";
  }

}
