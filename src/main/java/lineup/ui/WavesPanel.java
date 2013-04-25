package lineup.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import lineup.model.Wave;
import lineup.world.World;

public class WavesPanel extends JPanel {

  private static final long serialVersionUID = -4084865003899603288L;
  
  private Font font = new Font("SansSerif", Font.PLAIN, 9);
  
  /**
   * Constructor.
   * @param width
   */
  public WavesPanel(int width) {
    super();
    setSize(width-20, 30);
    setPreferredSize(getSize());
    setBackground(Color.DARK_GRAY);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    
    g.setFont(font);
    g.setColor(Color.WHITE);
    g.drawString(getMsg(World.getInstance()), 0, 10);
  }

  private String getMsg(World world) {
    if (world.isOnWaveCooldown()) {
      int time = world.getWaveCooldown()/1000;
      return "Next wave in " + time;
    } else {
      Wave current = world.getCurrentWave();
      if (current == null) {
        return "Final wave";
      } else {
        return current.toString();
      }
    }
  }
}
