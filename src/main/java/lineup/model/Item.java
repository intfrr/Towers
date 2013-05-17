package lineup.model;

/**
 * Thing that can't store other things.
 * @author Neil
 *
 */
public class Item {

  private int size;

  /**
   * Constructor.
   * @param size
   */
  public Item(int size) {
    this.size = size;
  }
  
  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
  
}
