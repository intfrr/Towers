package lineup.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import lineup.model.Bunker;
import lineup.model.Bunker.BunkerType;
import lineup.ui.UI.Mode;
import lineup.world.World;

public class ViewMouseListener implements MouseListener {

  private UI ui;
  private BunkerPanel bunkerPanel;
  
  
  /**
   * Constructor
   * @param bunkerPanel
   */
  public ViewMouseListener(UI ui, BunkerPanel bunkerPanel) {
    this.ui = ui;
    this.bunkerPanel = bunkerPanel;
  }

  public void mouseClicked(MouseEvent evt) {
    if (evt.getButton() == MouseEvent.BUTTON2) {
      ui.setMode(Mode.NORMAL, null);
    } else {
      if (ui.getMode() == Mode.NORMAL) {
        Bunker bunker = World.getInstance().getBunkerAtLocation(evt.getPoint());
        bunkerPanel.setBunker(bunker);
      } else if (ui.getMode() == Mode.BUILD_BUNKER) {
        buildBunker(ui.getModeObject(), evt.getPoint());
        ui.setMode(Mode.NORMAL, null);
      }
    }
  }

  private void buildBunker(Object modeObject, Point point) {
    BunkerType type = (BunkerType)modeObject;
    Bunker bunker = Bunker.create(type, point.x, point.y);
    
    World.getInstance().getBunkers().add(bunker);
    World.getInstance().removeMoney(bunker.getCost());
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
