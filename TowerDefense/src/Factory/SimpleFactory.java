
package Factory;

import edu.moravian.model.Enemy;
import edu.moravian.view.SpriteRenderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class SimpleFactory extends enemyFactory{
    private SpriteRenderer enemyRender;
    private ArrayList<Enemy> enemyMembers = new ArrayList<>();
    private ArrayList<SpriteRenderer> enemyRenders = new ArrayList<>();
    private Image enemyEntity;
    public SimpleFactory(int hitPoints) throws SlickException
    {
        super ("Simple", hitPoints);
        enemyEntity = new Image("res/Cool.png");
        enemyRender = new SpriteRenderer(enemyEntity);
    }
       
    @Override
    public Collection<Enemy> getEnemies() {
    while (enemyMembers.size()<2)
        try {
            enemyMembers.add(new Enemy(hitPoints));
        } catch (SlickException ex) {
            Logger.getLogger(SimpleFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return enemyMembers;
    }
    
    public Collection <SpriteRenderer> getEnemyRenderer()
    {
        while (enemyRenders.size()<enemyMembers.size())
        {
            enemyRenders.add(enemyRender);
        }
        return enemyRenders;
    }
    
    public Enemy getEnemyAt(int index) {
    return enemyMembers.get(index);
  }
    public SpriteRenderer getEnemyRenderAt(int index)
    {
        return enemyRenders.get(index);
    }
    public int arraySize(){
        return enemyMembers.size();
    }
}
