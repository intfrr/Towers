package lineup.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lineup.exception.DoesntFitException;

/**
 * Basic Container implementation.
 * @author Neil
 *
 */
public class Storage implements Container {

  private final int size;
  private final List<Item> contents = new ArrayList<Item>();
  
  /**
   * Constructor.
   * @param size
   */
  public Storage(int size) {
    this.size = size;
  }
  
  public int getSize() {
    return size;
  }

  public int freeSpace() {
    int used = size;
    for (Item item : contents) {
      used = used - item.getSize();
    }
    return used;
  }

  public void add(Item item) throws DoesntFitException {
    if (item.getSize() <= freeSpace()) {
      contents.add(item);
    } else {
      throw new DoesntFitException("Item " + item + " doesn't fit in " + this);
    }
  }

  public void remove(Item item) {
    contents.remove(item);
  }

  public Collection<Item> getItems() {
    return new ArrayList<Item>(contents);
  }


}
