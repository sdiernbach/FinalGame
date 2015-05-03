
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
    public void Execute(Enemy agentEntity) 
    {
        this.agentEntity = agentEntity;
       
        d = new Distance();
        if(d.Distance(agentEntity.getEntityMap(), bulletEntity.getEntityMap())<5)
        {
            this.performAction(agentEntity);
            
        }
        else if(health == 0 && energy == 0 || energy != 0)
            try {
                agentEntity.changeState(GetFood.getInstance());
        } catch (SlickException ex) {
            Logger.getLogger(GoAndChase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        else if (health == 0 && energy == 0 || energy == 0)
        {
            agentEntity.changeState(Sleep.getInstance());
        }
        
        else if (d.Distance(agentEntity.getEntityMap(), playerEntity.getEntityMap())>5)
            try {
                agentEntity.changeState(Wander.getInstance());
        } catch (SlickException ex) {
            Logger.getLogger(GoAndChase.class.getName()).log(Level.SEVERE, null, ex);
        }
            }

    @Override
    public void performAction(Enemy agentEntity) 
    {
        int playerMX = playerEntity.getEntityMX();
        int playerMY = playerEntity.getEntityMY();
        int agentMX = agentEntity.getEntityMX();
        int agentMY = agentEntity.getEntityMY();
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
            agentEntity.setEntityWX(agentWX);
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
            agentEntity.setEntityWY(agentWY);
        }
    }
}
