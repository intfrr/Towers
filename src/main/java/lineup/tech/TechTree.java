package lineup.tech;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import lineup.model.Arms;
import lineup.model.TrackingSystem;
import lineup.model.implementations.arms.heavy.AutoCannon;
import lineup.model.implementations.arms.heavy.Cannon;
import lineup.model.implementations.arms.special.CruiseLauncher;

import lineup.model.implementations.arms.special.BurstLaser;
import lineup.model.implementations.arms.special.Flamer;
import lineup.model.implementations.arms.special.LightRocketLauncher;
import lineup.model.implementations.arms.special.MultipleLauncher;
import lineup.model.implementations.arms.special.PulseLaser;
import lineup.model.implementations.arms.light.GatlingGun;
import lineup.model.implementations.arms.light.HeavyMG;
import lineup.model.implementations.arms.light.LightMG;
import lineup.model.implementations.trackers.BasicTracker;
import lineup.model.implementations.trackers.HighPowerBasicTracker;
import lineup.model.implementations.trackers.MediumPowerBasicTracker;
import lineup.model.implementations.trackers.NearestPriorityScanner;

/**
 * Class that manages the tech tree for various things.
 * @author Neil
 */
public class TechTree {
  
  private Map<Class<? extends Arms>,Node<Arms>> armsMap = new LinkedHashMap<Class<? extends Arms>,Node<Arms>>();
  private Map<Class<? extends TrackingSystem>,Node<TrackingSystem>> trackerMap = new LinkedHashMap<Class<? extends TrackingSystem>,Node<TrackingSystem>>();
  
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
    
    TrackingSystem nearest = new NearestPriorityScanner();
    
    Node<TrackingSystem> basicNode = new Node<TrackingSystem>(basic);
    Node<TrackingSystem> medPowerNode = new Node<TrackingSystem>(medPower);
    Node<TrackingSystem> hiPowerNode = new Node<TrackingSystem>(hiPower);
    
    Node<TrackingSystem> nNode = new Node<TrackingSystem>(nearest);
    
    basicNode.addUpgrade(medPowerNode);
    basicNode.addUpgrade(nNode);
    medPowerNode.addUpgrade(hiPowerNode);
    
    trackerMap.put(basic.getClass(), basicNode);
    trackerMap.put(medPower.getClass(), medPowerNode);
    trackerMap.put(hiPower.getClass(), hiPowerNode);
    trackerMap.put(nearest.getClass(), nNode);
  }

  
  private void buildArmsTechTree() {
    buildLightTree();
    buildHeavyTree();
    buildSpecialTree();
  }
  
  
  private void buildLightTree() {
    Arms lmg = new LightMG(null);
    Arms hmg = new HeavyMG(null);
    Arms gg = new GatlingGun(null);
    
    Node<Arms> lmgNode = new Node<Arms>(lmg);
    Node<Arms> hmgNode = new Node<Arms>(hmg);
    Node<Arms> ggNode = new Node<Arms>(gg);
    
    lmgNode.addUpgrade(hmgNode);
    hmgNode.addUpgrade(ggNode);
    
    armsMap.put(lmg.getClass(), lmgNode);
    armsMap.put(hmg.getClass(), hmgNode);
    armsMap.put(gg.getClass(), ggNode);
  }
  
  private void buildHeavyTree() {
    Arms ac = new AutoCannon(null);
    Arms cannon = new Cannon(null);
    
    Node<Arms> acNode = new Node<Arms>(ac);
    Node<Arms> cannonNode = new Node<Arms>(cannon);
    
    acNode.addUpgrade(cannonNode);
    
    armsMap.put(cannon.getClass(), cannonNode);
    armsMap.put(ac.getClass(), acNode);
  }

  private void buildSpecialTree() {
    Arms lrl = new LightRocketLauncher(null);
    Arms ml = new MultipleLauncher(null);
    Arms cl = new CruiseLauncher(null);
    
    Arms plas = new PulseLaser(null);
    Arms blas = new BurstLaser(null);
    
    Arms flam = new Flamer(null);
    
    Node<Arms> lrlNode = new Node<Arms>(lrl);
    Node<Arms> mlNode = new Node<Arms>(ml);
    Node<Arms> clNode = new Node<Arms>(cl);
    
    Node<Arms> plasNode = new Node<Arms>(plas);
    Node<Arms> blasNode = new Node<Arms>(blas);
    
    Node<Arms> flamNode = new Node<Arms>(flam);
    
    lrlNode.addUpgrade(mlNode);
    lrlNode.addUpgrade(plasNode);
    lrlNode.addUpgrade(flamNode);
    mlNode.addUpgrade(clNode);
    plasNode.addUpgrade(blasNode);
    
    armsMap.put(plas.getClass(), plasNode);
    armsMap.put(blas.getClass(), blasNode);
    armsMap.put(lrl.getClass(), lrlNode);
    armsMap.put(ml.getClass(), mlNode);
    armsMap.put(cl.getClass(), clNode);
    armsMap.put(flam.getClass(), flamNode);
  }

  public Set<Arms> getUpgrades(Arms arms) {
    if (arms == null) {
      Set<Arms> first = new HashSet<Arms>();
      first.add(new AutoCannon(null));
      first.add(new LightMG(null));
      first.add(new LightRocketLauncher(null));
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
