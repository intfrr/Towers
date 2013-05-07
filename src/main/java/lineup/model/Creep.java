package lineup.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import lineup.util.math.Vector2D;
import lineup.world.Renderable;
import lineup.world.Updateable;

public abstract class Creep implements Renderable, Updateable {

  private Vector2D vector;
  private int maxHealth;
  private int health;
  private Deque<Location> route;
  private String name;
  
  public Creep(String name, double velocity, int health, List<Location> route) {
    this.name = name;
    this.health = health;
    this.maxHealth = health;
    this.route = new LinkedList<Location>(route);
    
    Location position = route.get(0);
    Location target = route.get(1);
    this.vector = new Vector2D(position, target, velocity);
  }
  
  public void update(int elapsed) {
    double time = (double)elapsed/1000.0;
    
    Location destination = route.getFirst();
    
    if (destination.equals(vector.getLocation())) {
      route.removeFirst();
      vector.pointAt(route.getFirst());
    } else {
    
      //Set new location
      double distanceX = destination.x - vector.getX();
      double distanceY = destination.y - vector.getY();
      
      double dx = vector.getDx() * time;
      double dy = vector.getDy() * time;
      
      //Don't go past target
      dx = dx > 0 ? Math.min(distanceX, dx) : Math.max(distanceX, dx);
      dy = dy > 0 ? Math.min(distanceY, dy) : Math.max(distanceY, dy);
      
      vector.translate(dx, dy);
    }
  }
  
  
  public void render(Graphics g) {
    if (health < maxHealth) {
      int barsize = health * getSize() / maxHealth;
      g.setColor(Color.GREEN);
      g.fillRect((int)vector.getX(), (int)vector.getY() - 2, barsize, 1);
      g.setColor(Color.RED);
      g.fillRect((int)vector.getX()+barsize, (int)vector.getY() - 2, getSize() - barsize, 1);
    }
    renderCreep(g);
  }
  
  protected abstract void renderCreep(Graphics g);

  public Location getLocation() {
    return vector.getLocation();
  }
  
  public Location getCentreLocation() {
    return new Location(vector.getX() + getSize()/2, vector.getY() + getSize()/2);
  }

  public int getHealth() {
    return health;
  }
  
  public void setHealth(int health) {
    this.health = health;
  }

  public String getName() {
    return name;
  }

  public Deque<Location> getRoute() {
    return route;
  }
  
  public Rectangle getBoundingRect() {
    return new Rectangle((int)vector.getX(), (int)vector.getY(), getSize(), getSize());
  }
  
  public Vector2D getVector() {
    return vector;
  }

  public abstract Creep copy();
  
  public abstract int getSize();
  
  public abstract int getValue();

}
