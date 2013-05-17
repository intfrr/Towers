package lineup.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class ControlPanel extends JPanel {

  private static final long serialVersionUID = -7398328203130803688L;
  
  private BunkerPanel bunkerPanel;
  private StatsPanel statsPanel;
  private WavesPanel wavesPanel;
  
  public ControlPanel(int width, UI ui) {
    super();
    setSize(width, 180);
    setPreferredSize(getSize());
    setBorder(BorderFactory.createRaisedBevelBorder());
    setBackground(Color.DARK_GRAY);
    
    statsPanel = new StatsPanel(width);
    bunkerPanel = new BunkerPanel(width);
    wavesPanel = new WavesPanel(ui, width);
    add(statsPanel);
    add(bunkerPanel);
    add(wavesPanel);
  }

  public BunkerPanel getBunkerPanel() {
    return bunkerPanel;
  }
  
}
