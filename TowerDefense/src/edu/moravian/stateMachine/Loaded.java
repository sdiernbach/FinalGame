
package edu.moravian.stateMachine;

import edu.moravian.math.Distance;
import edu.moravian.model.Bullet;
import towerdefense.Game;
import edu.moravian.model.Enemy;
import edu.moravian.model.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;


public class Loaded implements AgentState
{
    private static Loaded instance;
    
    
    private Enemy agentEntity;
    private Bullet bulletEntity;
    private double agentWX, agentWY;
    private Distance d;
    
    
    private Loaded()
    {
       // houseEntity= Game.getInstance().getHouseEntity();
    }
    
    public static Loaded getInstance()
    {
        if(instance == null)
            instance = new Loaded();
        return instance;
    }

    @Override
    public void Execute(Bullet bulletEntity) 
    {
        this.bulletEntity = bulletEntity;
        d = new Distance();
        if(d.Distance(bulletEntity.getEntityMap(), agentEntity.getEntityMap())>5)
        {
            this.performAction(bulletEntity);            
        }
        
        else if(d.Distance(bulletEntity.getEntityMap(), agentEntity.getEntityMap())<5)
            bulletEntity.changeState(FireAndChase.getInstance());
        
    }

    @Override
    public void performAction(Bullet bulletEntity) 
    {
        
    }

    
}
