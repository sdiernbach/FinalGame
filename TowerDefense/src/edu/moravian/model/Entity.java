
package edu.moravian.model;

import towerdefense.Game;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.Point2D;
import org.newdawn.slick.SlickException;


public abstract class Entity 
{
    protected final CoordinateTranslator ct;
    protected int delta, entityMX, entityMY;
    protected double entityWX, entityWY;
    protected Point2D entityPoint2D;
    
    public Entity() throws SlickException
    {
        this.ct = Game.getInstance().getCT();
    }
    
    public abstract void update();
    public abstract void reset();

    public int getEntityMX() {
        return entityMX;
    }

    public int getEntityMY() {
        return entityMY;
    }

    public double getEntityWX() {
        return entityWX;
    }

    public double getEntityWY() {
        return entityWY;
    }
    
    public Point2D getEntityWorld()
    {
        entityPoint2D = new Point2D (this.getEntityWX(), this.getEntityWY());
       return entityPoint2D;
    }
    
    public Point2D getEntityMap()
    {
       entityPoint2D = new Point2D (this.getEntityWX(), this.getEntityWX());
       return entityPoint2D;
    }
}
