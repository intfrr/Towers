package lineup.model;

public class PoweredItem extends Item {

  private int power;

  /**
   * Constructor.
   * @param size
   * @param power
   */
  public PoweredItem(int size, int power) {
    super(size);
    this.power = power;
  }

  public int getPower() {
    return power;
  }
  
}
