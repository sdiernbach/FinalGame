
package edu.moravian.view;

import towerdefense.Game;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.model.Entity;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;


public class SpriteRenderer
{
    private int drawX, drawY;
    private final Renderable renderable;
    private final CoordinateTranslator ct;

    public SpriteRenderer(Renderable renderable) throws SlickException
    {
        ct = Game.getInstance().getCT();
        this.renderable = renderable;
    }

    public void update(Entity spriteEntity)
    {
        drawX =  spriteEntity.getEntityMX();
           
        drawY =spriteEntity.getEntityMY();
  
    }
    
    public void render(Graphics grphcs)
    {
       
            renderable.draw(drawX, drawY);
        
    }

    
}
