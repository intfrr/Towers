package lineup.model.implementations.projectiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import lineup.model.Bunker;
import lineup.model.Creep;
import lineup.model.Projectile;
import lineup.ui.util.ImageLoader;

public class CruiseMissile extends Projectile {

  private static double velocity = 25.0;
  private Creep target;
  private Image sprite;
  private AffineTransform identity = new AffineTransform();
  
  public CruiseMissile(Bunker owner, int x, int y, double bearing, Creep target) {
    super(owner, x, y, velocity/1000.0, bearing, 5);
    this.target = target;
    sprite = ImageLoader.loadSprite("cruisemissile.png");
  }

  public void render(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    AffineTransform xform = new AffineTransform();
    xform.setTransform(identity);
    xform.translate(getX(), getY());
    xform.rotate(getBearing() + Math.PI/2, 3, 3);
    
    g2.drawImage(sprite, xform, null);
  }

  /**
   * Trackes the locked on creep target.
   */
  public void update(int elapsed) {
    updateBearing();
    double time = elapsed;
    double dx = Math.cos(getBearing()) * getVelocity() * time;
    double dy = Math.sin(getBearing()) * getVelocity() * time;
    
    setX(getX() + dx);
    setY(getY() + dy);
  }
  
  private void updateBearing() {
    double dx = target.getCentreLocation().x - getX();
    double dy = target.getCentreLocation().y - getY();
    setBearing(Math.atan2(dy, dx));
  }
  

  @Override
  public String toString() {
    return getX() + "," + getY() + " b" + getBearing() + " locked on " + target;
  }

  @Override
  public int getDamage() {
    return 20;
  }

}
