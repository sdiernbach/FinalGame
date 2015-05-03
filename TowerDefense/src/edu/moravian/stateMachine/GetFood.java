
package edu.moravian.stateMachine;
import edu.moravian.main.Game;
import edu.moravian.math.AStar.Path;
import edu.moravian.math.PathFinder;
import edu.moravian.model.Enemy;
import edu.moravian.model.Berry;
import edu.moravian.model.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;


public class GetFood implements AgentState
{
    private static GetFood instance;
    private final Entity berryEntity1;
    private final PathFinder pf;
    private Path p;
    //private final Entity berryEntity2;
   // private final Entity berryEntity3;
    private GetFood() throws SlickException
    {
        this.pf = new PathFinder();
        
        berryEntity1 = Game.getInstance().getBerryEntity();
//    berryEntity1 = Game.getInstance().getBerryEntity(0);
    //berryEntity2 = Game.getInstance().getBerryEntity(1);
    //berryEntity3 = Game.getInstance().getBerryEntity(2)
    }
    
    public static GetFood getInstance() throws SlickException
    {
        if(instance == null)
            instance = new GetFood();
        return instance;
    }

    @Override
    public void Execute(Enemy agentEntity) 
    {
        int health = agentEntity.getHealth();
        if(agentEntity.getEnergy()!=0 && health!=agentEntity.getMaxHealth())
        {
            if(agentEntity.getEntityMap()!=berryEntity1.getEntityMap())
            {
                int energy = agentEntity.getEnergy();
                pf.generatePath(agentEntity.getEntityMap(), berryEntity1.getEntityMap());
                p = pf.getPath();
                this.performAction(agentEntity);
                energy -= 1;
                agentEntity.setEnergy(energy);
            }
            else
            {
                health+=3;
                agentEntity.setHealth(health);
            }
        }
        else if (agentEntity.getEnergy()==0)
        {
            agentEntity.changeState(Sleep.getInstance());
        }
        else
        {
            try {
                agentEntity.changeState(Wander.getInstance());
            } catch (SlickException ex) {
                Logger.getLogger(GetFood.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void performAction(Enemy agentEntity) 
    {
        
        int berryMX = berryEntity1.getEntityMX();
        int berryMY = berryEntity1.getEntityMY();
        int agentMX = agentEntity.getEntityMX();
        int agentMY = agentEntity.getEntityMY();
        double agentWX = 0;
        double agentWY = 0;
        int delta = Game.getInstance().getDelta();
        if(berryMX != agentMX)
        {
            if(berryMX > agentMX)
            {
                if((berryMX - agentMX) > Game.getInstance().getWorldWidth()*32/2)
                {
                    agentWX = (agentEntity.getEntityWX() -1*delta / 80.0);
                    if(agentWX < 0)
                        agentWX = Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth()/Game.getInstance().getScreenWidth();
                }
                else
                    agentWX =(agentEntity.getEntityWX() +1*delta / 80.0) % ((Game.getInstance().getWorldWidth()*32*Game.getInstance().getWorldWidth())/Game.getInstance().getScreenWidth());
            }
            else if(berryMX < agentMX)
            {
                if((agentMX - berryMX) > Game.getInstance().getWorldWidth()*32/2)
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
        if(berryMY != agentMY)
        {
            if(berryMY > agentMY)
            {
                if((berryMY - agentMY) > Game.getInstance().getWorldHeight()*32/2)
                {
                    agentWY = (agentEntity.getEntityWY() -1*delta / 80.0);
                    if(agentWY < 0)
                        agentWY = Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight()/Game.getInstance().getScreenHeight();
                }
                else
                    agentWY =(agentEntity.getEntityWY() +1*delta / 80.0) % ((Game.getInstance().getWorldHeight()*32*Game.getInstance().getWorldHeight())/Game.getInstance().getScreenHeight());
            }
            else if(berryMY < agentMY)
            {
                if((agentMY - berryMY) > Game.getInstance().getWorldHeight()*32/2)
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
