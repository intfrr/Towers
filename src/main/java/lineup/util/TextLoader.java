package lineup.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Utility class to load text resources.
 * @author Neil
 *
 */
public class TextLoader {

  public static String loadText(String name) {
    return load("/text/" + name + ".txt");
  }
  
  private static String load(String path) {
    BufferedReader reader = null;
    
    try {
      StringBuilder buf = new StringBuilder();
      String line = null;
      reader = new BufferedReader(new InputStreamReader(TextLoader.class.getResourceAsStream(path)));
      while ((line = reader.readLine()) != null) {
        buf.append(line);
      }
      return buf.toString();
    } catch (IOException io) {
        throw new RuntimeException("Failed to load text " + path, io);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (Exception ignore) {}
      }
    }
  }
}
