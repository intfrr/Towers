package lineup.game;

import lineup.ui.UI;
import lineup.util.LevelFactory;
import lineup.world.World;

public class ConfigGame {

  private UI ui;
  
  public static void main(String[] args) {
    ConfigGame game = new ConfigGame(300, 200);
    game.start();
  }

  public ConfigGame(int width, int height) {
    World.init(width, height);
    ui = new UI(width, height);
    
    LevelFactory factory = LevelFactory.getInstance();
    World.getInstance().setLevel(factory.getLevel(0));
    
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
