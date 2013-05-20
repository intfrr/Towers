package lineup.tech;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import lineup.model.Arms;
import lineup.model.TrackingSystem;
import lineup.model.implementations.arms.heavy.AutoCannon;
import lineup.model.implementations.arms.heavy.BigBertha;
import lineup.model.implementations.arms.heavy.Cannon;
import lineup.model.implementations.arms.heavy.HeavyCannon;
import lineup.model.implementations.arms.special.CruiseLauncher;

import lineup.model.implementations.arms.special.BurstLaser;
import lineup.model.implementations.arms.special.Flamer;
import lineup.model.implementations.arms.special.LightRocketLauncher;
import lineup.model.implementations.arms.special.MultipleLauncher;
import lineup.model.implementations.arms.special.NapalmLauncher;
import lineup.model.implementations.arms.special.PulseLaser;
import lineup.model.implementations.arms.light.GatlingGun;
import lineup.model.implementations.arms.light.HeavyMG;
import lineup.model.implementations.arms.light.LightMG;
import lineup.model.implementations.arms.light.Militia;
import lineup.model.implementations.trackers.BasicThreatAnalyser;
import lineup.model.implementations.trackers.BasicTracker;
import lineup.model.implementations.trackers.HighPowerBasicTracker;
import lineup.model.implementations.trackers.HighPowerThreatAnalyser;
import lineup.model.implementations.trackers.MediumPowerBasicTracker;
import lineup.model.implementations.trackers.ThreatAnalyser;

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
    
    addTracker(basic, null);
    addTracker(medPower, basic);
    addTracker(hiPower, medPower);
    
    TrackingSystem nearest = new BasicThreatAnalyser();
    TrackingSystem nearest2 = new ThreatAnalyser();
    TrackingSystem nearest3 = new HighPowerThreatAnalyser();
    
    addTracker(nearest, basic);
    addTracker(nearest2, nearest);
    addTracker(nearest3, nearest2);
  }

  
  private void buildArmsTechTree() {
    buildLightTree();
    buildHeavyTree();
    buildSpecialTree();
  }
  
  
  private void buildLightTree() {
    Arms lmg = new LightMG(null);
    Arms hmg = new HeavyMG(null);
    Arms mil = new Militia(null);
    Arms gg = new GatlingGun(null);
    
    addArms(lmg, null);
    addArms(hmg, lmg);
    addArms(mil, hmg);
    addArms(gg, mil);
  }
  
  
  private void buildHeavyTree() {
    Arms ac = new AutoCannon(null);
    Arms cannon = new Cannon(null);
    Arms hvy = new HeavyCannon(null);
    Arms big = new BigBertha(null);
    
    addArms(ac, null);
    addArms(cannon, ac);
    addArms(hvy, cannon);
    addArms(big, hvy);
  }
  

  private void buildSpecialTree() {
    Arms lrl = new LightRocketLauncher(null);
    Arms ml = new MultipleLauncher(null);
    Arms cl = new CruiseLauncher(null);
    
    Arms plas = new PulseLaser(null);
    Arms blas = new BurstLaser(null);
    
    Arms flam = new Flamer(null);
    Arms nap = new NapalmLauncher(null);
    
    addArms(lrl, null);
    addArms(ml, lrl);
    addArms(cl, ml);
    addArms(plas, lrl);
    addArms(blas, plas);
    addArms(flam, lrl);
    addArms(nap, flam);
  }
  
  
  private void addTracker(TrackingSystem t, TrackingSystem parent) {
    Node<TrackingSystem> node = new Node<TrackingSystem>(t);
    trackerMap.put(t.getClass(), node);
    if (parent != null) {
      Node<TrackingSystem> parentNode = trackerMap.get(parent.getClass());
      parentNode.addUpgrade(node);
    }
  }
  
  
  private void addArms(Arms a, Arms parent) {
    Node<Arms> node = new Node<Arms>(a);
    armsMap.put(a.getClass(), node);
    if (parent != null) {
      Node<Arms> parentNode = armsMap.get(parent.getClass());
      parentNode.addUpgrade(node);
    }
  }


  public Set<Arms> getUpgrades(Arms arms) {
    if (arms == null) {
      Set<Arms> first = new LinkedHashSet<Arms>();
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
      Set<TrackingSystem> first = new LinkedHashSet<TrackingSystem>();
      first.add(new BasicTracker());
      return first;
    } else {
      return trackerMap.get(tracker.getClass()).getUpgrades();
    }
  }
}
