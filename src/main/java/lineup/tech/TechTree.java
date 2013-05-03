package lineup.tech;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lineup.model.Arms;
import lineup.model.TrackingSystem;
import lineup.model.implementations.arms.Cannon;
import lineup.model.implementations.arms.CruiseLauncher;
import lineup.model.implementations.trackers.BasicTracker;
import lineup.model.implementations.trackers.HighPowerBasicTracker;
import lineup.model.implementations.trackers.MediumPowerBasicTracker;

/**
 * Class that manages the tech tree for various things.
 * @author Neil
 */
public class TechTree {
  
  private Map<Class<? extends Arms>,Node<Arms>> armsMap = new HashMap<Class<? extends Arms>,Node<Arms>>();
  private Map<Class<? extends TrackingSystem>,Node<TrackingSystem>> trackerMap = new HashMap<Class<? extends TrackingSystem>,Node<TrackingSystem>>();
  
  /**
   * Constructor
   */
  public TechTree() {
    buildTrackingTechTree();
    buildArmsTechTree();
  }


  private void buildTrackingTechTree() {
    TrackingSystem basic = new BasicTracker();
    TrackingSystem medPower = new MediumPowerBasicTracker();
    TrackingSystem hiPower = new HighPowerBasicTracker();
    
    Node<TrackingSystem> basicNode = new Node<TrackingSystem>(basic);
    Node<TrackingSystem> medPowerNode = new Node<TrackingSystem>(medPower);
    Node<TrackingSystem> hiPowerNode = new Node<TrackingSystem>(hiPower);
    
    basicNode.addUpgrade(medPowerNode);
    medPowerNode.addUpgrade(hiPowerNode);
    
    trackerMap.put(basic.getClass(), basicNode);
    trackerMap.put(medPower.getClass(), medPowerNode);
    trackerMap.put(hiPower.getClass(), hiPowerNode);
  }

  
  private void buildArmsTechTree() {
    Arms cannon = new Cannon(null);
    Arms launcher = new CruiseLauncher(null);
    
    Node<Arms> cannonNode = new Node<Arms>(cannon);
    Node<Arms> launcherNode = new Node<Arms>(launcher);
    
    cannonNode.addUpgrade(launcherNode);
    
    armsMap.put(cannon.getClass(), cannonNode);
    armsMap.put(launcher.getClass(), launcherNode);
  }
  
  
  public Set<Arms> getUpgrades(Arms arms) {
    if (arms == null) {
      Set<Arms> first = new HashSet<Arms>();
      first.add(new Cannon(null));
      return first;
    } else {
      return armsMap.get(arms.getClass()).getUpgrades();
    }
  }
  
  public Set<TrackingSystem> getUpgrades(TrackingSystem tracker) {
    if (tracker == null) {
      Set<TrackingSystem> first = new HashSet<TrackingSystem>();
      first.add(new BasicTracker());
      return first;
    } else {
      return trackerMap.get(tracker.getClass()).getUpgrades();
    }
  }
}
