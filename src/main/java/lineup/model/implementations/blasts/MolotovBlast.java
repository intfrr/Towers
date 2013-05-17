package lineup.model.implementations.blasts;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import lineup.model.Blast;
import lineup.model.Bunker;

public class MolotovBlast extends Blast {

  private static final double size = 20.0;
  
  /**
   * Constructor.
   * @param x
   * @param y
   * @param owner
   */
  public MolotovBlast(double x, double y, Bunker owner) {
    super(new Ellipse2D.Double(x-size/2, y-size/2, size, size), owner, 1.0, 3000);
    setPersistant(true);
  }

  @Override
  public void render(Graphics2D g2) {
    super.render(g2);
    g2.fill(getShape());
  }
  
  

}
