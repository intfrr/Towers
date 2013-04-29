package lineup.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lineup.model.Bunker.BunkerType;
import lineup.ui.UI.Mode;

public class CreateBunkerListener implements ActionListener {

  private UI ui;
  
  /**
   * Constructor.
   * @param ui
   */
  public CreateBunkerListener(UI ui) {
    this.ui = ui;
  }

  
  public void actionPerformed(ActionEvent evt) {

    BunkerType type = BunkerType.valueOf(evt.getActionCommand());
    
    ui.setMode(Mode.BUILD_BUNKER, type);
    
  }

}
