
package edu.moravian.model;

import edu.moravian.math.Point2D;
import towerdefense.Game;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;


public class Enemy extends Entity
{
    private int  turn;
    //private final Entity enemyEntity;
    private Point2D World;
    private Point Map;
    private boolean dead;
    public Enemy(int healthPoints) throws SlickException
    {
        //enemyEntity=  Game.getInstance().getEnemyEntity();
        entityMX=-10;
        entityMY=65;
        turn=0;
        dead=false;
    }

    @Override
    public void update() 
    {
        //try {
          //  Point entityCo = ct.worldToScreen(Game.getInstance().getX(), Game.getInstance().getY());
        //} catch (SlickException ex) {
        //    Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
        //}
        
       
      if (turn==0)
      {
          if(entityMX != 575)
              entityMX = entityMX+(1);
          if(entityMX == 575)
              turn=1;
      }
      
      if(turn==1)
      {
          if(entityMY != 320)
              entityMY = entityMY+(1);
          if(entityMY == 320)
              turn=2;
      }
      if(turn==2)
      {
          if(entityMX != 225)
              entityMX = entityMX-(1);
          if(entityMX == 225)
              turn=3;
      }
      if(turn==3)
      {
          if(entityMY != 195)
              entityMY = entityMY-(1);
          if(entityMY == 195)
              turn=4;
      }
      if(turn==4)
      {
          if(entityMX != 95)
              entityMX = entityMX-(1);
          if(entityMX == 95)
              turn=5;
      }
      if(turn==5)
      {
          if(entityMY != 705)
              entityMY = entityMY+(1);
          if(entityMY == 705)
              turn=6;
      }
      if(turn==6)
      {
          if(entityMX != 225)
              entityMX = entityMX+(1);
          if(entityMX == 225)
              turn=7;
      }
      if(turn==7)
      {
          if(entityMY != 577)
              entityMY = entityMY-(1);
          if(entityMY == 577)
              turn=8;
      }
      if(turn==8)
      {
          if(entityMX != 800)
              entityMX = entityMX+(1);
          if(entityMX == 800)
          {
              try {
                  Game.getInstance().healthPoints -=1;
              } catch (SlickException ex) {
                  Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
              }
              turn=9;
          }
      }
      if (turn==9)
         dead=true;
     
    }

    @Override
    public void reset() 
    {
    }
}
