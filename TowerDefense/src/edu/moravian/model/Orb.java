
package edu.moravian.model;

import java.awt.Point;


public class Orb extends Entity
{
    public Orb()
    {
        entityWX = (int) (Math.random()*((ct.getWorldWidth()*32)*ct.getWorldWidth()/ct.getScreenWidth()));
        entityWY = (int) (Math.random()*((ct.getWorldHeight()*32)*ct.getWorldHeight()/ct.getScreenHeight()));
    }

    @Override
    public void update() 
    {
        Point entityCo = ct.worldToScreen(entityWX, entityWY);
        entityMX = (int) entityCo.getX();
        entityMY = (int)(entityCo.getY() - ct.getScreenHeight())*-1;
    }
    
    @Override
    public void reset()
    {
        entityWX = (int) (Math.random()*((ct.getWorldWidth()*32)*ct.getWorldWidth()/ct.getScreenWidth()));
        entityWY = (int) (Math.random()*((ct.getWorldHeight()*32)*ct.getWorldHeight()/ct.getScreenHeight()));
    }
}
