package lineup.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import lineup.model.Creep;
import lineup.model.Level;
import lineup.model.Location;
import lineup.model.Wave;
import lineup.util.config.CreepConfig;
import lineup.util.config.LevelConfig;
import lineup.util.config.MainConfig;
import lineup.util.config.WaveConfig;
import lineup.util.config.Waypoint;

public class LevelFactory {

  private List<LevelConfig> levelConfig;
  private Map<String,Constructor<Creep>> creepMap = new HashMap<String,Constructor<Creep>>();
  private static LevelFactory instance = new LevelFactory();
  
  /**
   * Constructor.
   */
  private LevelFactory() {
    try {
      JAXBContext ctx = JAXBContext.newInstance("lineup.util.config");
      Unmarshaller u = ctx.createUnmarshaller();
      
      Source source = new StreamSource(getClass().getClassLoader().getResourceAsStream("mainConfig.xml"));
      MainConfig mainConfig = u.unmarshal(source, MainConfig.class).getValue();
      
      levelConfig = mainConfig.getLevels().getLevel();
      Collections.sort(levelConfig, new Comparator<LevelConfig>() {
        public int compare(LevelConfig o1, LevelConfig o2) {
          return o1.getSequence().compareTo(o2.getSequence());
        }
      });
      
      for (CreepConfig creepConfig : mainConfig.getCreeps().getCreep()) {
        String fq = creepConfig.getFqName();
        Constructor<Creep> ctr = (Constructor<Creep>) Class.forName(fq).getDeclaredConstructors()[0];
        creepMap.put(creepConfig.getRef(), ctr);
      }
      
    } catch (Exception ex) {
      throw new RuntimeException("Config could not be loaded", ex);
    }
    
  }
  
  
  public static LevelFactory getInstance() {
    return instance;
  }
  
  public Level getLevel(int i) {
    Level level = new Level();
    
    LevelConfig config = levelConfig.get(i);
    level.setBase(createLocation(config.getBase()));
    level.setBackground(loadBackground(config.getBackground()));
    
    try {
      List<Location> route = createRoute(config.getRoute().getWaypoint());
      level.setWaves(createWaves(config.getWaves().getWave(), route));
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create waves for level " + i, ex);
    } 
    
    return level;
  }

  
  private Image loadBackground(String path) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(getClass().getResourceAsStream(path));
    } catch (IOException io) {
      throw new RuntimeException("Failed to load background image " + path, io);
    }
    return img;
  }


  private List<Location> createRoute(List<Waypoint> waypoints) {
    List<Location> route = new ArrayList<Location>(waypoints.size());
    for (Waypoint wp : waypoints) {
      route.add(createLocation(wp));
    }
    return route;
  }


  private Deque<Wave> createWaves(List<WaveConfig> waveList, List<Location> route) throws Exception {
    Deque<Wave> waves = new LinkedList<Wave>();
    
    Collections.sort(waveList, new Comparator<WaveConfig>() {
      public int compare(WaveConfig o1, WaveConfig o2) {
        return o1.getSequence().compareTo(o2.getSequence());
      }
    });
    
    for (WaveConfig config : waveList) {
      int interval = config.getInterval();
      int count = config.getCount();
      Creep creep = createCreep(config.getCreepRef(), route);
      waves.add(new Wave(interval, count, creep));
    }
    
    return waves;
  }


  private Creep createCreep(String ref, List<Location> route) throws Exception {
    Constructor<Creep> ctr = creepMap.get(ref);
    return ctr.newInstance(route);
  }


  private Location createLocation(Waypoint wp) {
    return new Location(wp.getX(), wp.getY());
  }
}
