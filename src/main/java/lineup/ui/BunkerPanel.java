package lineup.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import lineup.model.Arms;
import lineup.model.Bunker;
import lineup.model.TrackingSystem;
import lineup.tech.TechTree;
import lineup.world.World;

public class BunkerPanel extends JPanel implements ActionListener {

  private static final long serialVersionUID = 7007533619332443955L;

  private Font font = new Font("SansSerif", Font.PLAIN, 9);
  private Bunker bunker;
  private TechTree techTree = new TechTree();
  
  private JButton upgradeArms = new JButton("upgrade");
  private JButton upgradeTracker = new JButton("upgrade");
  
  /**
   * Constructor.
   * @param width
   */
  public BunkerPanel(int width) {
    super();
    setSize(width/2-10, 90);
    setPreferredSize(getSize());
    setBackground(Color.DARK_GRAY);
    setBorder(BorderFactory.createLoweredBevelBorder());
    
    SpringLayout layout = new SpringLayout();
    setLayout(layout);
    
    upgradeArms.setToolTipText("Upgrade weapons");
    upgradeArms.setFont(font);
    upgradeArms.setBackground(Color.DARK_GRAY);
    upgradeArms.setForeground(Color.WHITE);
    Border border = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), 
        BorderFactory.createMatteBorder(0, 2, 0, 2, Color.DARK_GRAY));
    upgradeArms.setBorder(border);
    upgradeArms.addActionListener(this);
    upgradeArms.setVisible(false);
    
    add(upgradeArms);
    layout.putConstraint(SpringLayout.NORTH, upgradeArms, 42, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.EAST, upgradeArms, -5, SpringLayout.EAST, this);
    
    upgradeTracker.setToolTipText("Upgrade tracker");
    upgradeTracker.setFont(font);
    upgradeTracker.setBackground(Color.DARK_GRAY);
    upgradeTracker.setForeground(Color.WHITE);
    upgradeTracker.setBorder(border);
    upgradeTracker.addActionListener(this);
    upgradeTracker.setVisible(false);
    
    add(upgradeTracker);
    layout.putConstraint(SpringLayout.NORTH, upgradeTracker, 12, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.EAST, upgradeTracker, -5, SpringLayout.EAST, this);
  }

  
  public void setBunker(Bunker bunker) {
    this.bunker = bunker;
    upgradeArms.setVisible(bunker != null);
    upgradeTracker.setVisible(bunker != null);
    
    if (bunker != null) {
      upgradeArms.setEnabled(!techTree.getUpgrades(bunker.getArms()).isEmpty());
      upgradeTracker.setEnabled(!techTree.getUpgrades(bunker.getTracking()).isEmpty());
    }
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

  
  public void actionPerformed(ActionEvent evt) {
    if (evt.getSource() == upgradeArms) {
      showArmsUpgradeMenu();
    } else if (evt.getSource() == upgradeTracker) {
      showTrackerUpgradeMenu();
    } else {
      String clazz = evt.getActionCommand();
      upgradeBunker(clazz);
    }
  }


  private void showArmsUpgradeMenu() {
    JPopupMenu popup = new JPopupMenu("Upgrade");
    
    for (Arms arms : techTree.getUpgrades(bunker.getArms())) {
      JMenuItem item = new JMenuItem(arms.getName() + " $" + arms.getCost());
      item.setActionCommand(arms.getClass().getCanonicalName());
      item.addActionListener(this);
      if (World.getInstance().getMoney() < arms.getCost()) {
        item.setEnabled(false);
      }
      popup.add(item);
    }
    
    popup.show(upgradeArms, -100, 0);
  }

  

  private void showTrackerUpgradeMenu() {
    JPopupMenu popup = new JPopupMenu("Upgrade");
    
    for (TrackingSystem tracker : techTree.getUpgrades(bunker.getTracking())) {
      JMenuItem item = new JMenuItem(tracker.getName() + " $" + tracker.getCost());
      item.setActionCommand(tracker.getClass().getCanonicalName());
      item.addActionListener(this);
      if (World.getInstance().getMoney() < tracker.getCost()) {
        item.setEnabled(false);
      }
      popup.add(item);
    }
    
    popup.show(upgradeTracker, -100, 0);
  }
  
  
  private void upgradeBunker(String clazz) {
    try {
      Class c = Class.forName(clazz);
      if (Arms.class.isAssignableFrom(c)) {
        upgradeArms(c);
      } else if (TrackingSystem.class.isAssignableFrom(c)) {
        upgradeTracker(c);
      }
      
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create class " + clazz, ex);
    }
  }


  private void upgradeTracker(Class<? extends TrackingSystem> c) {
    try {
      TrackingSystem tracker = c.newInstance();
      World.getInstance().removeMoney(tracker.getCost());
      bunker.setTracking(tracker);
      upgradeTracker.setEnabled(!techTree.getUpgrades(bunker.getTracking()).isEmpty());
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create tracker for class " + c, ex);
    }
  }


  private void upgradeArms(Class<? extends Arms> c) {
    try {
      Arms arms = (Arms) c.getConstructors()[0].newInstance(bunker);
      World.getInstance().removeMoney(arms.getCost());
      bunker.setArms(arms);
      upgradeArms.setEnabled(!techTree.getUpgrades(bunker.getArms()).isEmpty());
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create arms for class " + c, ex);
    }
    
  }

}
