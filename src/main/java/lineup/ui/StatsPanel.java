package lineup.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import lineup.world.World;

public class StatsPanel extends JPanel {

  private static final long serialVersionUID = 8464051588293757119L;

  private Font font = new Font("SansSerif", Font.PLAIN, 9);
  
  public StatsPanel(int width) {
    super();
    setSize(width/2-10, 90);
    setPreferredSize(getSize());
    setBackground(Color.DARK_GRAY);
    setBorder(BorderFactory.createLoweredBevelBorder());
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    
    g.setFont(font);
    
    g.setColor(Color.WHITE);
    int creeps = World.getInstance().getCreeps().size();
    g.drawString(creeps + " creeps", 5, 10);
    
    int lives = World.getInstance().getLives();
    g.drawString(lives + " lives", 5, 20);
    
    g.setColor(Color.YELLOW);
    g.drawString("$ " + World.getInstance().getMoney(), 5, 80);
  }
}
