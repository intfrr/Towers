package lineup.game;

import lineup.model.Bunker;
import lineup.model.Creep;
import lineup.model.Level;
import lineup.model.Location;
import lineup.model.Wave;
import lineup.model.implementations.BlueCreep;
import lineup.model.implementations.SmallBunker;
import lineup.ui.UI;
import lineup.world.World;

public class TestGame {

  private UI ui;
  
  public static void main(String[] args) {
    TestGame game = new TestGame(300, 200);
    game.start();
  }

  public TestGame(int width, int height) {
    ui = new UI(width, height);
    World.init(width, height);
    
    
    Level level = new Level();
    
    level.getWaypoints().add(new Location(10, 177));
    level.getWaypoints().add(new Location(190, 90));
    level.setBase(new Location(70, 70));
    
    Creep creep = new BlueCreep(level.getWaypoints());
    Wave wave1 = new Wave(4, 3, creep);
    level.getWaves().add(wave1);
    
    World.getInstance().setLevel(level);
    
    Bunker bunker = new SmallBunker();
    bunker.setLocation(60, 60);
    World.getInstance().getBunkers().add(bunker);
    
    ui.launch();
  }
  
  private void start() {
    
    long time = System.nanoTime();
    boolean won = false;
    boolean lost = false;
    while (!won && !lost) {
      ui.display(World.getInstance());
      long elapsed = System.nanoTime() - time;
      time = System.nanoTime();
      World.getInstance().update((int) (elapsed/1000000));
      
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      won = World.getInstance().hasWon();
      lost = World.getInstance().hasLost();
    }
    
    ui.end(won);
  }
}
