package lineup.util.ui;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class GraphicsUtil {

  
  public static void drawWrappedString(Graphics g, String s, int x, int y, int width) {
    FontMetrics fm = g.getFontMetrics();
    int lineHeight = fm.getHeight();

    int curX = x;
    int curY = y;

    for (String word : s.split(" ")) {

      int wordWidth = fm.stringWidth(word + " ");

      if (curX + wordWidth >= x + width) {
        curY += lineHeight;
        curX = x;
      }

      g.drawString(word, curX, curY);
      curX += wordWidth;
    }
  }
}
