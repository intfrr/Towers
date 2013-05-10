package lineup.model.implementations.projectiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import lineup.model.Bunker;
import lineup.model.Creep;
import lineup.model.Location;
import lineup.model.Projectile;
import lineup.util.ui.ImageLoader;
import lineup.util.math.Vector2D;

public class SmallMissile extends Projectile {

  private static double velocity = 50.0;
  private static double maxTurnPerSec = 1.5;
  private Creep target;
  private Image sprite;
  private AffineTransform identity = new AffineTransform();
  
  public SmallMissile(Bunker owner, int x, int y, double bearing, Creep target) {
    super(owner, new Vector2D(new Location(x, y), velocity, bearing), 2);
    this.target = target;
    sprite = ImageLoader.loadSprite("smallmissile.png");
  }

  public void render(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    AffineTransform xform = new AffineTransform();
    xform.setTransform(identity);
    xform.translate(getVector().getX(), getVector().getY());
    xform.rotate(getVector().getBearing() + Math.PI/2, 3, 3);
    
    g2.drawImage(sprite, xform, null);
  }

  /**
   * Limited homing on the locked on creep target.
   */
  public void update(int elapsed) {
    double time = (double)elapsed / 1000.0;
    
    if (target.getHealth() > 0) {
      double targetOffset = new Vector2D(getVector().getLocation(), target.getCentreLocation()).getBearing();
      double diff = targetOffset - getVector().getBearing();
      if (Math.abs(diff) > Math.PI) {
        diff = diff - Math.PI*2*Math.signum(diff);
      }
      double maxTurn = time * maxTurnPerSec * Math.signum(diff);
      double limit = limit(diff, maxTurn);
      getVector().turn(limit);
    }
     
    getVector().translateTime(time);
  }

  private double limit(double diff, double maxTurn) {
    return Math.abs(diff) > Math.abs(maxTurn) ? maxTurn : diff;
  }

  @Override
  public String toString() {
    return "SmallMissile " + getVector() + " locked on " + target;
  }

  @Override
  public int getDamage() {
    return 5;
  }

}
