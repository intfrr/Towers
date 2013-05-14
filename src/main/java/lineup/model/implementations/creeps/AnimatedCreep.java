package lineup.model.implementations.creeps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lineup.model.Creep;
import lineup.model.Location;
import lineup.util.ui.ImageLoader;

public abstract class AnimatedCreep extends Creep {
  
  private AffineTransform identity = new AffineTransform();
  private int time;
  private boolean imageRotates;
  private Image[] sprites;
  private int spriteIndex;
  
  /**
   * Constructor.
   * @param name
   * @param velocity
   * @param health
   * @param route
   */
  public AnimatedCreep(String name, double velocity, int health, List<Location> route, boolean imageRotates) {
    super(name, velocity, health, route);
    this.time = getFrameCycleTime();
    this.imageRotates = imageRotates;
    loadSprites();
  }

  
  private void loadSprites() {
    URL url = getClass().getResource("/sprites/");

    String[] files;
    try {
      File spritesDir = new File(url.toURI());
      files = spritesDir.list();
    } catch (URISyntaxException e) {
      throw new RuntimeException("Can't load sprites", e);
    }
    
    List<String> spriteNames = new ArrayList<String>();
    
    for (String file : files) {
      if (file.startsWith(getSpriteName() + "_")) {
        spriteNames.add(file);
      }
    }
    
    Collections.sort(spriteNames, new Comparator<String>() {
      public int compare(String s1, String s2) {
        int i1 = Integer.parseInt(s1.substring(s1.indexOf("_")+1, s1.indexOf(".")));
        int i2 = Integer.parseInt(s2.substring(s2.indexOf("_")+1, s2.indexOf(".")));
        return i1 < i2 ? -1 : 1;
      }
    });
    
    sprites = new Image[spriteNames.size()];
    for (int i=0; i<sprites.length; i++) {
      sprites[i] = ImageLoader.loadSprite(spriteNames.get(i));
    }
    if (sprites.length == 0) {
      throw new RuntimeException("No sprites found with name " + getSpriteName());
    }
  }


  @Override
  public void update(int elapsed) {
    super.update(elapsed);
    time -= elapsed;
    if (time <= 0) {
      time = getFrameCycleTime();
      spriteIndex++;
      
      if (spriteIndex >= sprites.length) {
        spriteIndex = 0;
      }
    }
  }


  @Override
  protected void renderCreep(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    AffineTransform xform = new AffineTransform();
    xform.setTransform(identity);
    xform.translate(getVector().getX() - getWidth()/2, getVector().getY() - getHeight()/2);
    
    if (imageRotates) {  
      xform.rotate(getVector().getBearing(), getWidth()/2, getHeight()/2);
    }  
    
    Image sprite = sprites[spriteIndex];
    g2.drawImage(sprite, xform, null);
  }

  protected abstract int getFrameCycleTime();

  /**
   * This class looks for images with a name <code>spritename_n</code>
   * and loads all it finds.
   * @return
   */
  protected abstract String getSpriteName();

}
