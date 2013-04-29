package lineup.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;

public class BunkerButton extends JButton implements PropertyChangeListener {

  private static final long serialVersionUID = 968433757977853205L;

  private int threshold;
  
  /**
   * Constructor.
   * @param name
   */
  public BunkerButton(String name, int threshold) {
    super(name);
    this.threshold = threshold;
  }

  public void propertyChange(PropertyChangeEvent evt) {
    if ("money".equals(evt.getPropertyName())) {
      int newVal = (Integer)evt.getNewValue();
      setEnabled(newVal >= threshold);
    }
  }
  
  

}
