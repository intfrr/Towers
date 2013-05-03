package lineup.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import lineup.world.Renderable;
import lineup.world.Updateable;

public abstract class Creep implements Renderable, Updateable {

  private Location location;
  private int maxHealth;
  private int health;
  private double velocity;
  private Deque<Location> route;
  private String name;
  
  public Creep(String name, double velocity, int health, List<Location> route) {
    this.name = name;
    this.velocity = velocity;
    this.health = health;
    this.maxHealth = health;
    this.route = new LinkedList<Location>(route);
    this.location = route.get(0);
  }
  
  public void update(int elapsed) {
    Location destination = route.getFirst();
    
    if (destination.equals(location)) {
      //System.out.println("Arrived " + location);
      route.removeFirst();
    } else {
    
      //Set new location
      double distanceX = destination.x - location.x;
      double distanceY = destination.y - location.y;
      double bearing = Math.atan2(distanceY, distanceX);
      
      double dx = velocity * Math.cos(bearing);
      double dy = velocity * Math.sin(bearing);
      
      //Don't go past target
      dx = dx > 0 ? Math.min(distanceX, dx) : Math.max(distanceX, dx);
      dy = dy > 0 ? Math.min(distanceY, dy) : Math.max(distanceY, dy);
      
      location = new Location(location.x + dx, location.y = location.y + dy);
    }
  }
  
  
  public void render(Graphics g) {
    if (health < maxHealth) {
      int barsize = health * getSize() / maxHealth;
      g.setColor(Color.GREEN);
      g.fillRect((int)location.x, (int)location.y - 2, barsize, 1);
      g.setColor(Color.RED);
      g.fillRect((int)location.x+barsize, (int)location.y - 2, getSize() - barsize, 1);
    }
    renderCreep(g);
  }
  
  protected abstract void renderCreep(Graphics g);

  public Location getLocation() {
    return location;
  }
  
  public Location getCentreLocation() {
    return new Location(location.x + getSize()/2, location.y + getSize()/2);
  }

  public int getHealth() {
    return health;
  }
  
  public void setHealth(int health) {
    this.health = health;
  }

  public double getVelocity() {
    return velocity;
  }

  public void setVelocity(double velocity) {
    this.velocity = velocity;
  }

  public String getName() {
    return name;
  }

  public Deque<Location> getRoute() {
    return route;
  }
  
  public Rectangle getBoundingRect() {
    return new Rectangle((int)location.x, (int)location.y, getSize(), getSize());
  }

  public abstract Creep copy();
  
  public abstract int getSize();
  
  public abstract int getValue();

}
