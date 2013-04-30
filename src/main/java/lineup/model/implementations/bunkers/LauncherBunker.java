package lineup.model.implementations.bunkers;

import java.awt.Color;
import java.awt.Graphics;

import lineup.model.Bunker;
import lineup.model.implementations.trackers.HiPowerBasicTracker;
import lineup.model.implementations.arms.CruiseLauncher;

/**
 * Small bunker with 10 size.
 * Contains a CruiseLauncher and HiPowerBasicTracker.
 * @author Neil
 *
 */
public class LauncherBunker extends Bunker {

  private int size = 10;
  private Color color = Color.GRAY;
  
  /**
   * Constructor.
   */
  public LauncherBunker() {
    super(10, 100);
    setArms(new CruiseLauncher(this));
    setTracking(new HiPowerBasicTracker());
  }

  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int)getLocation().x, (int)getLocation().y, size, size);
  }

  @Override
  public String getName() {
    return "Launcher Bunker";
  }

}
