package lineup.model.implementations;

import java.awt.Color;
import java.awt.Graphics;

import lineup.model.Bunker;
import lineup.model.Projectile;

public class Shell extends Projectile {

  private static double velocity = 50.0; 
  private Color color = Color.RED;
  
  public Shell(Bunker owner, int x, int y, double bearing) {
    super(owner, x, y, velocity/1000.0, bearing, 2);
  }

  public void render(Graphics g) {
    g.setColor(color);
    g.fillOval((int)getX(), (int)getY(), getSize(), getSize());
  }

  /**
   * Flies in a straight line.
   */
  public void update(int elapsed) {
    double time = elapsed;
    double dx = Math.cos(getBearing()) * getVelocity() * time;
    double dy = Math.sin(getBearing()) * getVelocity() * time;
    
    setX(getX() + dx);
    setY(getY() + dy);
  }
  
  @Override
  public String toString() {
    return getX() + "," + getY() + " b" + getBearing();
  }

}
