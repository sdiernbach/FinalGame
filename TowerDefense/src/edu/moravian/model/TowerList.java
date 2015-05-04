
package edu.moravian.model;

import edu.moravian.view.SpriteRenderer;
import edu.moravian.view.TowerRenderer;
import java.util.ArrayList;
import java.util.Collection;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TowerList {
    private ArrayList<Tower> towerMembers = new ArrayList<>();
    private Tower tower;
    private Image towerEntity;
   
    
    private TowerRenderer  towerRenderer;
    
    public TowerList() throws SlickException
    {
      towerMembers = new ArrayList<>();  
      towerEntity = new Image("res/steve.png");
      towerRenderer = new TowerRenderer(towerEntity);
    }
    
    
    public void addTower(Tower tower)
    {
        towerMembers.add(tower);
        
    }
    
    public void removeTower(Tower tower)
    {
        if(towerMembers.contains(tower))
        {
           int remove = towerMembers.indexOf(tower);
            towerMembers.remove(remove);
        }
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
    
    public void redner()
    {
        for(int i=0; i<towerMembers.size();i++)
         towerRenderer.update(tower);
    }
    
    public int arraySize(){
        return towerMembers.size();
    }
    
    public Tower getTowerAt(int index)
    {
        return towerMembers.get(index);
    }
}
