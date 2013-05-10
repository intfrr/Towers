package lineup.ui;

import javax.swing.JDialog;

public class HelpDialog extends JDialog {

  private static final long serialVersionUID = 1955693620510632851L;

  /**
   * Constructor.
   */
  public HelpDialog() {
    super();
    setTitle("Help");
    setModal(false);
    setSize(300, 300);
  }
}
