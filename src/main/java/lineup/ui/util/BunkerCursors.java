package lineup.ui.util;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import lineup.model.Bunker.BunkerType;

public class BunkerCursors {
  
  public static Cursor getCursor(BunkerType type) {
    BufferedImage img = ImageLoader.loadSprite("bunker_" + type.name() + ".png");
    BufferedImage transparent = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
    transparent.getGraphics().drawImage(img, 0, 0, null);
    
    return Toolkit.getDefaultToolkit().createCustomCursor(transparent, new Point(0,0), "Bunker");
  }

}
