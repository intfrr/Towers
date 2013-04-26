package lineup.model.implementations.bunkers;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import lineup.model.Bunker;
import lineup.model.implementations.arms.Cannon;
import lineup.model.implementations.trackers.BasicTracker;

/**
 * Small bunker with 10 size.
 * Contains a Cannon and BasicTracker.
 * @author 38183Ne
 *
 */
public class SmallBunker extends Bunker {

  private int size = 10;
  private Color color = Color.GRAY;
  
  /**
   * Constructor.
   */
  public SmallBunker() {
    super(10);
    setArms(new Cannon(this));
    setTracking(new BasicTracker());
  }

  public void render(JPanel view) {
    Graphics g = view.getGraphics();
    g.setColor(color);
    g.fillRect((int)getLocation().x, (int)getLocation().y, size, size);
  }

  @Override
  public String getName() {
    return "Small Bunker";
  }

}
