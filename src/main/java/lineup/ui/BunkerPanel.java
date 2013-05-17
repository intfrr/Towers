package lineup.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Set;

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
import lineup.util.ui.GraphicsUtil;
import lineup.util.ui.ImageLoader;
import lineup.world.Player;
import lineup.world.World;

public class BunkerPanel extends JPanel implements ActionListener {

  private static final long serialVersionUID = 7007533619332443955L;
  private static Image background = ImageLoader.loadBackground("bunker.png");
  
  private Font font = new Font("SansSerif", Font.PLAIN, 9);
  private Font font2 = new Font("SansSerif", Font.BOLD, 11);
  
  private Bunker bunker;
  private Player player = World.getInstance().getPlayer();
  private TechTree techTree = new TechTree();
  
  private JButton upgradeArms = new JButton("upgrade");
  private JButton upgradeTracker = new JButton("upgrade");
  private JButton sell = new JButton("sell");
  
  private DecimalFormat df = new DecimalFormat("#.#");
  
  /**
   * Constructor.
   * @param width
   */
  public BunkerPanel(int width) {
    super();
    setSize(width/2+10, 120);
    setPreferredSize(getSize());
    setBackground(Color.DARK_GRAY);
    setBorder(BorderFactory.createLoweredBevelBorder());
    
    SpringLayout layout = new SpringLayout();
    setLayout(layout);
    
    Border border = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), 
        BorderFactory.createMatteBorder(0, 2, 0, 2, Color.DARK_GRAY));
    
    sell.setToolTipText("sell for half cost");
    sell.setFont(font);
    sell.setBackground(Color.DARK_GRAY);
    sell.setForeground(Color.WHITE);
    sell.setBorder(border);
    sell.addActionListener(this);
    sell.setVisible(false);
    
    add(sell);
    layout.putConstraint(SpringLayout.NORTH, sell, 2, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.EAST, sell, -5, SpringLayout.EAST, this);
    
    upgradeArms.setToolTipText("Upgrade weapons");
    upgradeArms.setFont(font);
    upgradeArms.setBackground(Color.DARK_GRAY);
    upgradeArms.setForeground(Color.WHITE);
    upgradeArms.setBorder(border);
    upgradeArms.addActionListener(this);
    upgradeArms.setVisible(false);
    
    add(upgradeArms);
    layout.putConstraint(SpringLayout.NORTH, upgradeArms, 58, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.EAST, upgradeArms, -5, SpringLayout.EAST, this);
    
    upgradeTracker.setToolTipText("Upgrade tracker");
    upgradeTracker.setFont(font);
    upgradeTracker.setBackground(Color.DARK_GRAY);
    upgradeTracker.setForeground(Color.WHITE);
    upgradeTracker.setBorder(border);
    upgradeTracker.addActionListener(this);
    upgradeTracker.setVisible(false);
    
    add(upgradeTracker);
    layout.putConstraint(SpringLayout.NORTH, upgradeTracker, 22, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.EAST, upgradeTracker, -5, SpringLayout.EAST, this);
  }

  
  public void setBunker(Bunker bunker) {
    this.bunker = bunker;
    sell.setVisible(bunker != null);
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
    g.setFont(font);
    g.setColor(Color.WHITE);
    String title = bunker.getName() + " (" + bunker.getKills() + " kills)";
    g.drawString(title, 5, 10);
    
    if (bunker.getTracking() == null) {
      g.setColor(Color.RED);
      g.drawString("No tracking system", 5, 30);
    } else {
      g.setColor(Color.YELLOW);
      g.drawString(bunker.getTracking().getName(), 5, 30);
      g.setColor(Color.GRAY);
      GraphicsUtil.drawWrappedString(g, bunker.getTracking().getDescription(), 6, 40, getWidth() - 50);
    }
    
    if (bunker.getArms() == null) {
      g.setColor(Color.RED);
      g.drawString("No weapon system", 5, 65);
    } else {
      g.setColor(Color.YELLOW);
      g.drawString(bunker.getArms().getName(), 5, 65);
      g.setColor(Color.GRAY);
      GraphicsUtil.drawWrappedString(g, bunker.getArms().getDescription(), 6, 75, getWidth() - 50);
    }
    
    paintBunkerInfo(g);
  }

  
  /**
   * Paints the stats at the bottom of the bunker panel.
   * @param g
   */
  private void paintBunkerInfo(Graphics g) {
    g.drawImage(background, 2, 92, this);
    
    if (bunker != null) {
      g.setColor(Color.GRAY);
      g.setFont(font2);
      g.drawString(String.valueOf(bunker.getFreePower()), 18, 113);
      g.drawString(String.valueOf(bunker.freeSpace()), 63, 113);
      if (bunker.getTracking() != null) {
        g.drawString(String.valueOf(bunker.getTracking().getRange()), 104, 113);
      }
      if (bunker.getArms() != null) {
        g.drawString(df.format(bunker.getArms().getDPS()), 138, 113);
      }
    }
  }


  public void actionPerformed(ActionEvent evt) {
    if (evt.getSource() == upgradeArms) {
      showArmsUpgradeMenu();
    } else if (evt.getSource() == upgradeTracker) {
      showTrackerUpgradeMenu();
    } else if (evt.getSource() == sell) {
      sellBunker();
    } else {
      String clazz = evt.getActionCommand();
      upgradeBunker(clazz);
    }
  }


  private void showArmsUpgradeMenu() {
    JPopupMenu popup = new JPopupMenu("Upgrade");
    
    for (Arms arms : techTree.getUpgrades(bunker.getArms())) {
      if (bunker.canUpgrade(arms)) {
        JMenuItem item = new JMenuItem(arms.getName() + " $" + arms.getCost());
        item.setToolTipText(arms.getDescription());
        item.setActionCommand(arms.getClass().getCanonicalName());
        item.addActionListener(this);
        if (player.getMoney() < arms.getCost()) {
          item.setEnabled(false);
        }
        popup.add(item);
      }
    }
    
    popup.show(upgradeArms, -100, 0);
  }

  

  private void showTrackerUpgradeMenu() {
    JPopupMenu popup = new JPopupMenu("Upgrade");
    
    for (TrackingSystem tracker : techTree.getUpgrades(bunker.getTracking())) {
      if (bunker.canUpgrade(tracker)) {
        JMenuItem item = new JMenuItem(tracker.getName() + " $" + tracker.getCost());
        item.setToolTipText(tracker.getDescription());
        item.setActionCommand(tracker.getClass().getCanonicalName());
        item.addActionListener(this);
        if (player.getMoney() < tracker.getCost()) {
          item.setEnabled(false);
        }
        popup.add(item);
      }
    }
    
    popup.show(upgradeTracker, -100, 0);
  }
  
  
  private void upgradeBunker(String clazz) {
    try {
      Class<?> c = Class.forName(clazz);
      if (Arms.class.isAssignableFrom(c)) {
        upgradeArms(c);
      } else if (TrackingSystem.class.isAssignableFrom(c)) {
        upgradeTracker(c);
      }
      
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create class " + clazz, ex);
    }
  }

  private void sellBunker() {
    if (bunker != null) {
      World.getInstance().getPlayer().giveMoney(bunker.getSellValue());
      World.getInstance().removeBunker(bunker);
      bunker = null;
      sell.setVisible(false);
      upgradeArms.setVisible(false);
      upgradeTracker.setVisible(false);
    }
  }

  private void upgradeTracker(Class<?> c) {
    try {
      TrackingSystem tracker = (TrackingSystem)c.newInstance();
      player.removeMoney(tracker.getCost());
      bunker.setTracking(tracker);
      Set<TrackingSystem> upgrades = techTree.getUpgrades(bunker.getTracking());
      upgradeTracker.setEnabled(!upgrades.isEmpty() && anyTrackersFit(upgrades));
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create tracker for class " + c, ex);
    }
  }


  private boolean anyTrackersFit(Set<TrackingSystem> upgrades) {
    for (TrackingSystem t : upgrades) {
      if (bunker.canUpgrade(t)) {
        return true;
      }
    }
    return false;
  }


  private void upgradeArms(Class<?> c) {
    try {
      Arms arms = (Arms) c.getConstructors()[0].newInstance(bunker);
      player.removeMoney(arms.getCost());
      bunker.setArms(arms);
      Set<Arms> upgrades = techTree.getUpgrades(bunker.getArms());
      upgradeArms.setEnabled(!upgrades.isEmpty() && anyArmsFit(upgrades));
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create arms for class " + c, ex);
    }
    
  }


  private boolean anyArmsFit(Set<Arms> upgrades) {
    for (Arms a : upgrades) {
      if (bunker.canUpgrade(a)) {
        return true;
      }
    }
    return false;
  }

}
