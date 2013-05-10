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

public class CruiseMissile extends Projectile {

  private static double velocity = 30.0;
  private Creep target;
  private Image sprite;
  private AffineTransform identity = new AffineTransform();
  
  public CruiseMissile(Bunker owner, int x, int y, double bearing, Creep target) {
    super(owner, new Vector2D(new Location(x, y), velocity, bearing), 5);
    this.target = target;
    sprite = ImageLoader.loadSprite("cruisemissile.png");
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
   * Tracks the locked on creep target.
   */
  public void update(int elapsed) {
    if (target.getHealth() > 0) {
      getVector().pointAt(target.getCentreLocation());
    }
    double time = (double)elapsed / 1000.0;
    getVector().translateTime(time);
  }
  
  
  

  @Override
  public String toString() {
    return "CruiseMissile " + getVector() + " locked on " + target;
  }

  @Override
  public int getDamage() {
    return 20;
  }

}
