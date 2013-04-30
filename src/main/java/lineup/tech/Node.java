package lineup.tech;

import java.util.HashSet;
import java.util.Set;

public class Node<T> {

  private T item;
  private Set<Node<T>> children = new HashSet<Node<T>>();

  
  /**
   * Constructor.
   * @param item
   */
  public Node(T item) {
    this.item = item;
  }

  public Set<T> getUpgrades() {
    Set<T> upgrades = new HashSet<T>();
    for (Node<T> node : children) {
      upgrades.add(node.getItem());
    }
    return upgrades;
  }
  
  public void addUpgrade(Node<T> upgrade) {
    children.add(upgrade);
  }
  
  public T getItem() {
    return item;
  }
}
