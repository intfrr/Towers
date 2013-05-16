package lineup.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import lineup.model.Creep;
import lineup.model.Wave;
import lineup.world.World;

public class WavesList extends JComponent {

  private static final long serialVersionUID = 5773149166155080586L;

  private Border border = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), 
      BorderFactory.createMatteBorder(0, 2, 0, 2, Color.DARK_GRAY));
  
  /**
   * Constructor.
   */
  public WavesList() {
    setBorder(border);
    ToolTipManager.sharedInstance().registerComponent(this);
  }

  @Override
  public Dimension getSize() {
    return new Dimension(150, 16);
  }

  @Override
  public Dimension getPreferredSize() {
    return getSize();
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    
    List<Wave> waves = new ArrayList<Wave>(World.getInstance().getLevel().getWaves());
    int offset = 2;
    for (Wave wave : waves) {
      Creep c = wave.getCreep().copy();
      offset += c.getWidth()/2 + 1;
      c.getVector().setX(offset);
      c.getVector().setY(7);
      c.getVector().setBearing(0);
      c.render(g);
      offset += c.getWidth()/2 + 1;
    }
  }

  @Override
  public String getToolTipText(MouseEvent event) {
    int x = event.getX();
    
    List<Wave> waves = new ArrayList<Wave>(World.getInstance().getLevel().getWaves());
    int offset = 2;
    for (Wave wave : waves) {
      Creep c = wave.getCreep().copy();
      offset += c.getWidth() + 2;
      if (offset >= x) {
        return wave.getCount() + " " + c.getName();
      }
    }
    return "Wave list";
  }
  
  
}
