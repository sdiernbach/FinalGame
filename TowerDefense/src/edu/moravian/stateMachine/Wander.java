
package edu.moravian.stateMachine;

import edu.moravian.main.Game;
import edu.moravian.math.AStar.Path;
import edu.moravian.math.Distance;
import edu.moravian.math.PathFinder;
import edu.moravian.math.Point2D;
import edu.moravian.model.Enemy;
import edu.moravian.model.Entity;
import org.newdawn.slick.SlickException;

public class Wander implements AgentState {

    private static Wander instance;
    private final Entity playerEntity;
    private Enemy agentEntity; 
    private Distance d;
    double houseMX = 40;
    double houseMY = 40;
    Point2D random;
    private final PathFinder pf;
    private Path p;
    
    
    private Wander() throws SlickException
    {
        playerEntity = Game.getInstance().getPlayerEntity();
        random= new Point2D (houseMX, houseMY);
        this.pf = new PathFinder();
    }
    
    public static Wander getInstance() throws SlickException
    {
        if(instance == null)
            instance = new Wander();
        return instance;
    }
    
    @Override
    public void Execute(Enemy agentEntity) {
        this.agentEntity = agentEntity;
        
        int energy = agentEntity.getEnergy();
        int health = agentEntity.getHealth();
        d = new Distance();
        
        /*if(houseMX==agentMX && houseMY==agentMY)
        {
            houseMX=10;
            houseMY=10;
        
        }*/
        if(d.Distance(this.agentEntity.getEntityMap(), playerEntity.getEntityMap())>5)
        {
            
            pf.generatePath(agentEntity.getEntityMap(), random);
                p = pf.getPath();
            this.performAction(agentEntity);
            energy -= 1;
            health -= 1; 
            
            agentEntity.setEnergy(energy);
            agentEntity.setHealth(health);
        }
        else
            agentEntity.changeState(GoAndChase.getInstance());
            
    }

    @Override
    public void performAction(Enemy agentEntity) {
        
        
        int agentMX = (int)agentEntity.getEntityWX();
        int agentMY = (int)agentEntity.getEntityWY();
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
