package lineup.model.implementations.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import lineup.model.Bunker;
import lineup.model.Creep;
import lineup.model.Projectile;
import lineup.util.math.Vector2D;

public class PulseLaserBeam extends Projectile {

  private int beamDuration = 1000;
  private Creep creep;

  /**
   * Constructor.
   * @param owner
   * @param creep
   */
  public PulseLaserBeam(Bunker owner, Creep creep) {
    super(owner, new Vector2D(owner.getCentreLocation(), creep.getCentreLocation()), 1);
    this.creep = creep;
  }

  public void render(Graphics g) {
    if (beamDuration < 100) {
      g.setColor(Color.WHITE);
    } else {
      g.setColor(Color.GREEN);
    }
    int x1 = (int)getOwner().getCentreLocation().x;
    int y1 = (int)getOwner().getCentreLocation().y;
    int x2 = (int)creep.getCentreLocation().x;
    int y2 = (int)creep.getCentreLocation().y;
    g.drawLine(x1, y1, x2, y2);
  }

  public void update(int elapsed) {
    beamDuration -= elapsed;
    
    if (beamDuration <= 0) {
      getVector().setX(creep.getCentreLocation().x);
      getVector().setY(creep.getCentreLocation().y);
    }
  }

  @Override
  public double getDamage() {
    return 5;
  }

}
