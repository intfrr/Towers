package lineup.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import lineup.model.Blast;
import lineup.model.Bunker;
import lineup.model.Bunker.BunkerType;
import lineup.model.Creep;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.util.ui.BunkerCursors;
import lineup.world.World;

public class UI {

  private JFrame frame;
  private Canvas view;
  private ControlPanel controls;
  
  public enum Mode {NORMAL, BUILD_BUNKER}
  private Mode mode = Mode.NORMAL;
  private Object modeObject;
  

  /**
   * Constructor.
   * @param width
   * @param height
   */
  public UI(int width, int height) {
    
    frame = new JFrame("Lineup");
    frame.setSize(width, height+168);
    
    view = new Canvas();
    view.setSize(width, height);
    view.setPreferredSize(view.getSize());
    
    controls = new ControlPanel(width, this);
    view.addMouseListener(new ViewMouseListener(this, controls.getBunkerPanel()));
    
    SpringLayout layout = new SpringLayout();
    layout.putConstraint(SpringLayout.NORTH, view, 0, SpringLayout.NORTH, frame);
    layout.putConstraint(SpringLayout.WEST, view, 0, SpringLayout.WEST, frame);
    layout.putConstraint(SpringLayout.NORTH, controls, 0, SpringLayout.SOUTH, view);
    layout.putConstraint(SpringLayout.WEST, controls, 0, SpringLayout.WEST, frame);
    frame.setLayout(layout);
    frame.add(view);
    frame.add(controls);
    
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(false);
  }
  
  
  public void launch() {
    frame.setVisible(true);
    view.createBufferStrategy(2);
  }
  
  
  public void display(World world) {
    Graphics g = null;
    try {
      g = view.getBufferStrategy().getDrawGraphics();
  
      g.drawImage(world.getLevel().getBackground(), 0, 0, null);
      
      for (Bunker b : world.getBunkers()) {
        b.borderRender(g);
      }
      
      for (Blast b : world.getBlasts()) {
        b.render((Graphics2D)g);
      }
      
      for (Creep c : world.getCreeps()) {
        c.render(g);
      }
      
      for (Projectile p : world.getProjectiles()) {
        p.render(g);
      }
      
      Location base = World.getInstance().getLevel().getBase();
      g.setColor(Color.ORANGE);
      g.fillOval((int)base.x - 5, (int)base.y - 5, 10, 10);
      
    } finally {
      if (g != null) {
        g.dispose();
      }
    }
    view.getBufferStrategy().show();
    
    controls.repaint();
  }

  
  public void end(boolean won) {
    if (won) {
      JOptionPane.showMessageDialog(frame, "You Won", "Killed all creeps", JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(frame, "Game Over", "Ran out of lives", JOptionPane.INFORMATION_MESSAGE);
    }
    frame.dispose();
  }
  
  
  public Mode getMode() {
    return mode;
  }

  
  public void setMode(Mode mode, Object o) {
    this.mode = mode;
    setModeObject(o);
    
    if (mode == Mode.NORMAL) {
      frame.setCursor(Cursor.getDefaultCursor());
    } else if (mode == Mode.BUILD_BUNKER) {
      BunkerType type = (BunkerType)o;
      frame.setCursor(BunkerCursors.getCursor(type));
    }
  }


  public Object getModeObject() {
    return modeObject;
  }


  public void setModeObject(Object modeObject) {
    this.modeObject = modeObject;
  }
}
