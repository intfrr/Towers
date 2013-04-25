package lineup.model;

import java.util.Collection;

import lineup.exception.DoesntFitException;

public interface Container {

  public int getSize();
  public int freeSpace();
  public void add(Item item) throws DoesntFitException;
  public void remove(Item item);
  public Collection<Item> getItems();
  
}
