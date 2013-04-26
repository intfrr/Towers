package lineup.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import lineup.model.Bunker;
import lineup.model.Creep;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.world.World;

public class UI {

  private JFrame frame;
  private JPanel view;
  private ControlPanel controls;
  
  public UI(int width, int height) {
    
    frame = new JFrame("Lineup");
    frame.setSize(width, height+168);
    
    view = new JPanel();
    view.setSize(width, height);
    view.setPreferredSize(view.getSize());
    
    controls = new ControlPanel(width);
    view.addMouseListener(new ViewMouseListener(controls.getBunkerPanel()));
    
    SpringLayout layout = new SpringLayout();
    layout.putConstraint(SpringLayout.NORTH, view, 0, SpringLayout.NORTH, frame);
    layout.putConstraint(SpringLayout.WEST, view, 0, SpringLayout.WEST, frame);
    layout.putConstraint(SpringLayout.NORTH, controls, 0, SpringLayout.SOUTH, view);
    layout.putConstraint(SpringLayout.WEST, controls, 0, SpringLayout.WEST, frame);
    frame.setLayout(layout);
    frame.add(view);
    frame.add(controls);
    
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setResizable(false);
  }
  
  public void launch() {
    frame.setVisible(true);
  }
  
  public void display(World world) {
    Graphics g = view.getGraphics();

    g.drawImage(world.getLevel().getBackground(), 0, 0, null);
    
    for (Bunker b : world.getBunkers()) {
      b.borderRender(view);
    }
    
    for (Creep c : world.getCreeps()) {
      c.render(view);
    }
    
    for (Projectile p : world.getProjectiles()) {
      p.render(view);
    }
    
    Location base = World.getInstance().getLevel().getBase();
    g.setColor(Color.ORANGE);
    g.fillOval((int)base.x - 5, (int)base.y - 5, 10, 10);
    
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
}
