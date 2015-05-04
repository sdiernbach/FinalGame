
package edu.moravian.stateMachine;

import towerdefense.Game;
import edu.moravian.math.Distance;
import edu.moravian.model.Bullet;
import edu.moravian.model.Enemy;
import edu.moravian.model.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;


public class FireAndChase implements AgentState
{
    private static FireAndChase instance;
    private Enemy agentEntity;
    private Bullet bulletEntity;
    private double agentWX, agentWY;
    private Distance d;
    
    private FireAndChase()
    {
        
    }
    
    public static FireAndChase getInstance()
    {
        if(instance == null)
            instance = new FireAndChase();
        return instance;
    }

    @Override
    public void Execute(Bullet bulletEntity) 
    {
        this.bulletEntity = bulletEntity;
        d = new Distance();
        if(d.Distance(bulletEntity.getEntityMap(), agentEntity.getEntityMap())<5)
        {
            this.performAction(bulletEntity);
            
        }
        
        
        else if (d.Distance(bulletEntity.getEntityMap(), agentEntity.getEntityMap())>5)
            
                bulletEntity.changeState(Loaded.getInstance());
        
        }

   

    @Override
    public void performAction(Bullet bulletEntity) 
    {
        int agentMX = bulletEntity.getEntityMX();
        int agentMY = bulletEntity.getEntityMY();
        int playerMX = agentEntity.getEntityMX();
        int playerMY = agentEntity.getEntityMY();
        int worldWidth = Game.getInstance().getWorldWidth();
        int worldHeight = Game.getInstance().getWorldHeight();
        int delta = Game.getInstance().getDelta();
        if(playerMX != agentMX)
        {
            if(playerMX > agentMX)
            {
                if((playerMX - agentMX) > Game.getInstance().getWorldWidth()*32/2)
                {
                    agentWX = (agentEntity.getEntityWX() -1*delta / 80.0);
                    if(agentWX < 0)
                        agentWX = Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth()/Game.getInstance().getScreenWidth();
                }
                else
                    agentWX =(agentEntity.getEntityWX() +1*delta / 80.0) % ((Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth())/Game.getInstance().getScreenWidth());
            }
            else if(playerMX < agentMX)
            {
                if((agentMX - playerMX) > Game.getInstance().getWorldWidth()*32/2)
                    agentWX =(agentEntity.getEntityWX() +1*delta / 80.0) % ((Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth())/Game.getInstance().getScreenWidth());
                else
                {
                    agentWX = (agentEntity.getEntityWX() -1*delta / 80.0);
                    if(agentWX < 0)
                        agentWX = Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth()/Game.getInstance().getScreenWidth();
                }
            }
            bulletEntity.setEntityWX(agentWX);
        }
        if(playerMY != agentMY)
        {
            if(playerMY > agentMY)
            {
                if((playerMY - agentMY) > Game.getInstance().getWorldHeight()*32/2)
                {
                    agentWY = (agentEntity.getEntityWY() -1*delta / 80.0);
                    if(agentWY < 0)
                        agentWY = Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight()/Game.getInstance().getScreenHeight();
                }
                else
                    agentWY =(agentEntity.getEntityWY() +1*delta / 80.0) % ((Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight())/Game.getInstance().getScreenHeight());
            }
            else if(playerMY < agentMY)
            {
                if((agentMY - playerMY) > Game.getInstance().getWorldHeight()*32/2)
                    agentWY =(agentEntity.getEntityWY() +1*delta / 80.0) % ((Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight())/Game.getInstance().getScreenHeight());
                else
                {
                    agentWY = (agentEntity.getEntityWY() -1*delta / 80.0);
                    if(agentWY < 0)
                        agentWX = Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight()/Game.getInstance().getScreenHeight();
                }
            }
            bulletEntity.setEntityWY(agentWY);
        }
    }
}
