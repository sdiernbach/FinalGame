package Factory;

import edu.moravian.model.Enemy;
import java.util.ArrayList;
import java.util.Collection;

public abstract class enemyFactory {
  private final String enemyType;
  
  
  protected int hitPoints;
  
  public enemyFactory(String enemyType, int hitPoints) {
    this.enemyType = enemyType;
    this.hitPoints = hitPoints;
  }
  
  public abstract Collection<Enemy> getEnemies();
  {
  
  }
  
  public String getEnemyType() {
    return enemyType;
  }
  
  public int getHP() {
    return hitPoints;
  }
  
 
  
  
  
}
