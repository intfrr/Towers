package lineup.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import lineup.util.TextLoader;
import lineup.util.ui.ImageLoader;

public class HelpDialog extends JDialog {

  private static final long serialVersionUID = 1955693620510632851L;
  
  private Font font = new Font("SansSerif", Font.PLAIN, 9);
  private Font font2 = new Font("SansSerif", Font.PLAIN, 11);
  private SpringLayout layout = new SpringLayout();

  private JTextArea overview;
  private JTextArea controls;
  private JLabel bunkerLabel;
  private JTextArea tech;
  
  
  /**
   * Constructor.
   */
  public HelpDialog() {
    super();
    setTitle("Help");
    setModal(false);
    setSize(350, 500);
    setLayout(layout);
    createOverview();
    createControlsHelp();
    createTechTreeHelp();
  }

  private void createOverview() {
    overview = new JTextArea();
    overview.setText(TextLoader.loadText("help_overview"));
    overview.setEnabled(false);
    overview.setWrapStyleWord(true);
    overview.setLineWrap(true);
    overview.setBackground(getBackground());
    overview.setDisabledTextColor(Color.DARK_GRAY);
    overview.setFont(font2);
    overview.setPreferredSize(new Dimension(getWidth() - 30, 100));
    add(overview);
    layout.putConstraint(SpringLayout.NORTH, overview, 5, SpringLayout.NORTH, this);
    layout.putConstraint(SpringLayout.WEST, overview, 5, SpringLayout.WEST, this);
  }

  private void createControlsHelp() {
    controls = new JTextArea();
    controls.setText(TextLoader.loadText("help_controls"));
    controls.setEnabled(false);
    controls.setWrapStyleWord(true);
    controls.setLineWrap(true);
    controls.setBackground(getBackground());
    controls.setDisabledTextColor(Color.DARK_GRAY);
    controls.setFont(font2);
    controls.setPreferredSize(new Dimension(getWidth() - 30, 100));
    add(controls);
    layout.putConstraint(SpringLayout.NORTH, controls, 5, SpringLayout.SOUTH, overview);
    layout.putConstraint(SpringLayout.WEST, controls, 5, SpringLayout.WEST, this);
    
    ImageIcon icon = new ImageIcon(ImageLoader.loadBackground("bunker.png"));
    String txt = TextLoader.loadText("bunker_symbols");
    bunkerLabel = new JLabel(txt, icon, SwingConstants.CENTER);
    bunkerLabel.setFont(font);
    bunkerLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
    bunkerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    
    add(bunkerLabel);
    layout.putConstraint(SpringLayout.NORTH, bunkerLabel, 5, SpringLayout.SOUTH, controls);
    layout.putConstraint(SpringLayout.WEST, bunkerLabel, 5, SpringLayout.WEST, this);
  }
  
  private void createTechTreeHelp() {
    tech = new JTextArea();
    tech.setText(TextLoader.loadText("help_tech"));
    tech.setEnabled(false);
    tech.setWrapStyleWord(true);
    tech.setLineWrap(true);
    tech.setBackground(getBackground());
    tech.setDisabledTextColor(Color.DARK_GRAY);
    tech.setFont(font2);
    tech.setPreferredSize(new Dimension(getWidth() - 30, 110));
    add(tech);
    layout.putConstraint(SpringLayout.NORTH, tech, 5, SpringLayout.SOUTH, bunkerLabel);
    layout.putConstraint(SpringLayout.WEST, tech, 5, SpringLayout.WEST, this);
  }

}
