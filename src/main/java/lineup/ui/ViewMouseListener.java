package lineup.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import lineup.model.Bunker;
import lineup.world.World;

public class ViewMouseListener implements MouseListener {

  private BunkerPanel bunkerPanel;
  
  
  /**
   * Constructor
   * @param bunkerPanel
   */
  public ViewMouseListener(BunkerPanel bunkerPanel) {
    this.bunkerPanel = bunkerPanel;
  }

  public void mouseClicked(MouseEvent evt) {
    Bunker bunker = World.getInstance().getBunkerAtLocation(evt.getPoint());
    bunkerPanel.setBunker(bunker);
  }

  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

}
