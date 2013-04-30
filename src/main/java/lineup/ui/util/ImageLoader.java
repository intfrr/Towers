package lineup.ui.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Utility class to load images.
 * @author Neil
 *
 */
public class ImageLoader {

  public static BufferedImage loadSprite(String name) {
    return load("/sprites/" + name);
  }

  public static Image loadBackground(String name) {
    return load("/backgrounds/" + name);
  }
  
  private static BufferedImage load(String path) {
    try {
      return ImageIO.read(ImageLoader.class.getResourceAsStream(path));
    } catch (IOException io) {
      throw new RuntimeException("Failed to load image " + path, io);
    }
  }
}
