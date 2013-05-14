package lineup.world;

import java.beans.PropertyChangeSupport;

import lineup.ui.BunkerButton;

public class Player {

  private int lives = 10;
  private Integer money = new Integer(0);
  private PropertyChangeSupport moneyProp = new PropertyChangeSupport(money);
  
  /**
   * Constructor.
   * @param lives
   * @param money
   */
  public Player(int lives, int money) {
    this.lives = lives;
    this.money = money;
  }

  public synchronized void giveMoney(int value) {
    money += value;
  }
  
  public synchronized void removeMoney(int cost) {
    money -= cost;
  }
  
  public int getMoney() {
    return money;
  }

  public void removeLife(int i) {
    lives -= i;
  }

  public int getLives() {
    return lives;
  }

  public void addMoneyListener(BunkerButton button) {
    moneyProp.addPropertyChangeListener(button);
  }





}
