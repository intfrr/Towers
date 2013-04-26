package lineup.model.implementations.projectiles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import lineup.model.Bunker;
import lineup.model.Creep;
import lineup.model.Projectile;

public class CruiseMissile extends Projectile {

  private static double velocity = 25.0;
  private Creep target;
  private Image sprite;
  private AffineTransform identity = new AffineTransform();
  
  public CruiseMissile(Bunker owner, int x, int y, double bearing, Creep target) {
    super(owner, x, y, velocity/1000.0, bearing, 5);
    this.target = target;
    sprite = loadSprite("/sprites/cruisemissile.png");
  }

  public void render(JPanel view) {
    Graphics2D g2 = (Graphics2D)view.getGraphics();
    AffineTransform xform = new AffineTransform();
    xform.setTransform(identity);
    xform.translate(getX(), getY());
    xform.rotate(getBearing() + Math.PI/2, 3, 3);
    
    g2.drawImage(sprite, xform, view);
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
  
  private Image loadSprite(String path) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(getClass().getResourceAsStream(path));
    } catch (IOException io) {
      throw new RuntimeException("Failed to load sprite image " + path, io);
    }
    return img;
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
