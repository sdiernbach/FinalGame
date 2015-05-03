
package edu.moravian.stateMachine;

import edu.moravian.main.Game;
import edu.moravian.model.Enemy;
import edu.moravian.model.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;


public class Sleep implements AgentState
{
    private static Sleep instance;
    private final Entity houseEntity;
    
    private Sleep()
    {
        houseEntity= Game.getInstance().getHouseEntity();
    }
    
    public static Sleep getInstance()
    {
        if(instance == null)
            instance = new Sleep();
        return instance;
    }

    @Override
    public void Execute(Enemy agentEntity) 
    {
        int energy = agentEntity.getEnergy();
        if(energy!=agentEntity.getMaxEnergy())
        {
            if(agentEntity.getEntityMX() != houseEntity.getEntityMX() || agentEntity.getEntityMY() != houseEntity.getEntityMY())
            {
                this.performAction(agentEntity);
            }
            else
            {
            energy+=10;
            agentEntity.setEnergy(energy);
            }
        }
        else
        {
            try {
                agentEntity.changeState(Wander.getInstance());
            } catch (SlickException ex) {
                Logger.getLogger(Sleep.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void performAction(Enemy agentEntity) 
    {
        
        int houseMX = houseEntity.getEntityMX();
        int houseMY = houseEntity.getEntityMY();
        int agentMX = agentEntity.getEntityMX();
        int agentMY = agentEntity.getEntityMY();
        double agentWX = 0;
        double agentWY = 0;
        int delta = Game.getInstance().getDelta();
        if(houseMX != agentMX)
        {
            if(houseMX > agentMX)
            {
                if((houseMX - agentMX) > Game.getInstance().getWorldWidth()*32/2)
                {
                    agentWX = (agentEntity.getEntityWX() -1*delta / 80.0);
                    if(agentWX < 0)
                        agentWX = Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth()/Game.getInstance().getScreenWidth();
                }
                else
                    agentWX =(agentEntity.getEntityWX() +1*delta / 80.0) % ((Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth())/Game.getInstance().getScreenWidth());
            }
            else if(houseMX < agentMX)
            {
                if((agentMX - houseMX) > Game.getInstance().getWorldWidth()*32/2)
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
        if(houseMY != agentMY)
        {
            if(houseMY > agentMY)
            {
                if((houseMY - agentMY) > Game.getInstance().getWorldHeight()*32/2)
                {
                    agentWY = (agentEntity.getEntityWY() -1*delta / 80.0);
                    if(agentWY < 0)
                        agentWY = Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight()/Game.getInstance().getScreenHeight();
                }
                else
                    agentWY =(agentEntity.getEntityWY() +1*delta / 80.0) % ((Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight())/Game.getInstance().getScreenHeight());
            }
            else if(houseMY < agentMY)
            {
                if((agentMY - houseMY) > Game.getInstance().getWorldHeight()*32/2)
                    agentWY =(agentEntity.getEntityWY() +1*delta / 80.0) % ((Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight())/Game.getInstance().getScreenHeight());
                else
                {
                    agentWY = (agentEntity.getEntityWY() -1*delta / 80.0);
                    if(agentWY < 0)
                        agentWY = Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight()/Game.getInstance().getScreenHeight();
                }
            }
            agentEntity.setEntityWY(agentWY);
        }
    }
}
