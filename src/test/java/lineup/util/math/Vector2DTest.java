package lineup.util.math;

import junit.framework.Assert;
import lineup.model.Location;

import org.junit.Test;


public class Vector2DTest {

  @Test
  public void constructorTest() {
    
    Location origin = new Location(0, 0);
    Location l1010 = new Location(10,10);
    Location l11 = new Location(1,1);
    
    Vector2D a = new Vector2D(origin, l1010);
    Vector2D b = new Vector2D(origin, Math.sqrt(200), Math.PI/4);
    Vector2D c = new Vector2D(origin, l11, Math.sqrt(200));
    Vector2D d = new Vector2D(0, 0, Math.sqrt(200), Math.PI/4);
    
    assertVectorEqual(a, b);
    assertVectorEqual(a, c);
    assertVectorEqual(a, d);
    
    assertVectorEqual(b, a);
    assertVectorEqual(b, c);
    assertVectorEqual(b, d);
    
    assertVectorEqual(c, a);
    assertVectorEqual(c, b);
    assertVectorEqual(c, d);
    
    assertVectorEqual(d, a);
    assertVectorEqual(d, b);
    assertVectorEqual(d, c);
    
  }
  
  private void assertVectorEqual(Vector2D a, Vector2D b) {
    Assert.assertEquals("x", a.getX(), b.getX());
    Assert.assertEquals("y", a.getY(), b.getY());
    Assert.assertEquals("mag", a.getMagnitude(), b.getMagnitude());
    Assert.assertEquals("bearing", a.getBearing(), b.getBearing());
  }

  @Test
  public void translationTest() {
    Vector2D x5 = new Vector2D(0, 0, 5, 0);
    x5.translate(5, 5);
    
    Assert.assertEquals("x coord after 5,5 translation ", 5, (int)x5.getX());
    Assert.assertEquals("y coord after 5,5 translation ", 5, (int)x5.getY()); 
  }
  
  @Test
  public void translateTimeTest() {
    Vector2D x5 = new Vector2D(0, 0, 5, 0);
    x5.translateTime(1000);
    Assert.assertEquals("x coord after 1000ms time ", 5000, (int)x5.getX());
    Assert.assertEquals("y coord after 1000ms time ", 0, (int)x5.getY());
  }
  
  @Test
  public void pointAtTest() {
    Vector2D l55 = new Vector2D(5, 5, 10, 0);
    l55.pointAt(new Location(0, 0));
    
    Assert.assertEquals("Point at magnitude", 10, (int)l55.getMagnitude());
    Assert.assertEquals("Point at bearing", -3*Math.PI/4, l55.getBearing());
  }
  
  @Test
  public void addTest() {
    Vector2D x5 = new Vector2D(0, 0, 5, 0);
    Vector2D y5 = new Vector2D(0, 0, 5, Math.PI/2);
    
    x5.add(y5);
    
    Assert.assertEquals("x after adding", 0.0, x5.getX());
    Assert.assertEquals("y after adding", 0.0, x5.getY());
    Assert.assertEquals("magnitude after adding", Math.sqrt(50), x5.getMagnitude());
    Assert.assertEquals("bearing after adding", Math.PI/4, x5.getBearing());
    Assert.assertEquals("dx after adding", 5, (int)x5.getDx());
    Assert.assertEquals("dy after adding", 5, (int)x5.getDy());
  }
  
}
