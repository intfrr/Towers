package lineup.model.implementations.bunkers;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import lineup.model.Bunker;
import lineup.model.implementations.trackers.HiPowerBasicTracker;
import lineup.model.implementations.arms.CruiseLauncher;

/**
 * Small bunker with 10 size.
 * Contains a CruiseLauncher and HiPowerBasicTracker.
 * @author 38183Ne
 *
 */
public class LauncherBunker extends Bunker {

  private int size = 10;
  private Color color = Color.GRAY;
  
  /**
   * Constructor.
   */
  public LauncherBunker() {
    super(10);
    setArms(new CruiseLauncher(this));
    setTracking(new HiPowerBasicTracker());
  }

  public void render(JPanel view) {
    Graphics g = view.getGraphics();
    g.setColor(color);
    g.fillRect((int)getLocation().x, (int)getLocation().y, size, size);
  }

  @Override
  public String getName() {
    return "Launcher Bunker";
  }

}
