package lineup.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import lineup.model.Bunker;

public class BunkerPanel extends JPanel {

  private static final long serialVersionUID = 7007533619332443955L;

  private Font font = new Font("SansSerif", Font.PLAIN, 9);
  private Bunker bunker;
  
  public BunkerPanel(int width) {
    super();
    setSize(width/2-10, 90);
    setPreferredSize(getSize());
    setBackground(Color.DARK_GRAY);
    setBorder(BorderFactory.createLoweredBevelBorder());
  }

  public void setBunker(Bunker bunker) {
    this.bunker = bunker;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    g.setFont(font);
    
    if (bunker != null) {
      paintBunker(g);
    }
  }

  private void paintBunker(Graphics g) {
    g.setColor(Color.WHITE);
    String title = bunker.getName() + " (" + bunker.getKills() + " kills)";
    g.drawString(title, 5, 10);
    
    if (bunker.getTracking() == null) {
      g.setColor(Color.RED);
      g.drawString("No tracking system", 5, 20);
    } else {
      g.setColor(Color.YELLOW);
      g.drawString(bunker.getTracking().getName(), 5, 20);
    }
    
    if (bunker.getArms() == null) {
      g.setColor(Color.RED);
      g.drawString("No weapon system", 5, 50);
    } else {
      g.setColor(Color.YELLOW);
      g.drawString(bunker.getArms().getName(), 5, 50);
    }
  }

}
