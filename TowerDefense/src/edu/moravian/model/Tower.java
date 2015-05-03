
package edu.moravian.model;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import towerdefense.Game;


public class Tower extends Entity {
    //private final Entity towerEntity;

    public Tower()throws SlickException
    {  
        entityMX=200;
        entityMY=200;
    }
    
    @Override
    public void update() {
        try {
            for (int r=0; r<Game.getInstance().getWorldHeight(); r++) {
                for (int c=0; c<Game.getInstance().getWorldWidth(); c++)
                {
                    try {
                        if(Game.getInstance().getTower()[r][c]==0)//Game.getInstance().getMap().getNode(r, c).isStart()==true)
                        {
                            int i=Game.getInstance().getTower()[r][c];
                            entityMX=r;
                            entityMY=c;
                        }
                    } catch (SlickException ex) {
                        Logger.getLogger(Tower.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SlickException ex) {
            Logger.getLogger(Tower.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void reset() {
       
    }
    
}
