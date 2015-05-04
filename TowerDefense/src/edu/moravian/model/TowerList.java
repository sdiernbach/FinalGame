
package edu.moravian.model;

import edu.moravian.view.SpriteRenderer;
import java.util.ArrayList;
import java.util.Collection;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TowerList {
    private ArrayList<Tower> towerMembers = new ArrayList<>();
    private Tower tower;
    private Image towerEntity;
   
    
    private SpriteRenderer  towerRenderer;
    
    public TowerList() throws SlickException
    {
      towerMembers = new ArrayList<>();  
      towerEntity = new Image("res/steve.png");
        towerRenderer = new SpriteRenderer(towerEntity);
    }
    
    
    public void addTower(Tower tower)
    {
        towerMembers.add(tower);
        
    }
    
    public void removeTower(Tower tower)
    {
        towerMembers.remove(tower);
    }
    
    public  Collection <Tower> getTowerMembers()
    {
        
        return towerMembers;
    }
    
    public void update (int delta)
    {
        for(int i=0; i<towerMembers.size();i++)
            towerMembers.get(i).update();
        
    }
}
