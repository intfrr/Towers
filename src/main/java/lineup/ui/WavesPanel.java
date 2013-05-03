package lineup.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import lineup.model.Bunker.BunkerType;
import lineup.model.Wave;
import lineup.world.World;

public class WavesPanel extends JPanel {

  private static final long serialVersionUID = -4084865003899603288L;
  
  private Font font9 = new Font("SansSerif", Font.PLAIN, 9);
  private Font fontBig = new Font("SansSerif", Font.PLAIN, 10);
  private SpringLayout layout;
  
  /**
   * Constructor.
   * @param ui
   * @param width
   */
  public WavesPanel(UI gameUi, int width) {
    super();
    setSize(width-20, 30);
    setPreferredSize(getSize());
    setBackground(Color.DARK_GRAY);
    
    createWidgets(gameUi);
  }

  private void createWidgets(UI gameUi) {
    layout = new SpringLayout();
    setLayout(layout);
    
    BunkerButton s = createButton(BunkerType.S, "Small Bunker $50", gameUi, 50);
    layout.putConstraint(SpringLayout.NORTH, s, 0, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, s, 145, SpringLayout.WEST, this);
    
    
    BunkerButton m = createButton(BunkerType.M, "Medium Bunker $100", gameUi, 100);
    layout.putConstraint(SpringLayout.NORTH, m, 0, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, m, 5, SpringLayout.EAST, s);
    
    BunkerButton l = createButton(BunkerType.L, "Large Bunker $200", gameUi, 200);
    layout.putConstraint(SpringLayout.NORTH, l, 0, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, l, 5, SpringLayout.EAST, m);
  }

  private BunkerButton createButton(BunkerType type, String tooltip, UI gameUi, int costThreshold) {
    BunkerButton button = new BunkerButton(type.name(), costThreshold);
    button.setToolTipText(tooltip);
    button.setFont(fontBig);
    button.setBackground(Color.DARK_GRAY);
    button.setForeground(Color.WHITE);
    Border border = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), 
        BorderFactory.createMatteBorder(0, 2, 0, 2, Color.DARK_GRAY));
    button.setBorder(border);
    button.setActionCommand(type.name());
    button.addActionListener(new CreateBunkerListener(gameUi));
    World.getInstance().getPlayer().addMoneyListener(button);
    add(button);
    return button;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    
    g.setFont(font9);
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
