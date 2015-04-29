
package edu.moravian.view;

import towerdefense.Game;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.model.Entity;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;


public class SpriteRenderer
{
    private int drawX, drawY;
    private boolean goUp, goDown, goLeft, goRight;
    private final Renderable renderable;
    private final CoordinateTranslator ct;

    public SpriteRenderer(Renderable renderable)
    {
        ct = Game.getInstance().getCT();
        this.renderable = renderable;
    }

    public void update(MapRenderer mapRenderer, Entity spriteEntity)
    {
        if(mapRenderer.getMapX() > ct.getWorldWidth()*32 - ct.getScreenWidth())
        {
            drawX = ct.getWorldWidth()*32 - mapRenderer.getMapX() + spriteEntity.getEntityMX();
            if(drawX < 0)
                drawX = drawX + ct.getWorldWidth()*32;
            if(drawX > ct.getWorldWidth()*32)
                drawX = drawX%(ct.getWorldWidth()*32);
        }
        else if (mapRenderer.getMapX() < 0)
        {
            drawX = -ct.getWorldWidth()*32 - mapRenderer.getMapX() + spriteEntity.getEntityMX();
            if(drawX < 0)
                drawX = drawX + ct.getWorldWidth()*32;
            if(drawX > ct.getWorldWidth()*32)
                drawX = drawX%(ct.getWorldWidth()*32);
        }
        else
        {
            drawX = spriteEntity.getEntityMX() - mapRenderer.getMapX();
        }
        
        if(mapRenderer.getMapY() < 0)
        {
            drawY = -ct.getWorldHeight()*32 - mapRenderer.getMapY() + spriteEntity.getEntityMY();
            if (drawY < 0)
                drawY = drawY + ct.getWorldHeight()*32;
            if (drawY > ct.getWorldHeight()*32)
                drawY = drawY%(ct.getWorldHeight()*32);
        }
        else if(mapRenderer.getMapY() > ct.getWorldHeight()*32 - ct.getScreenHeight())
        {
            drawY = ct.getWorldHeight()*32 - mapRenderer.getMapY() + spriteEntity.getEntityMY();
            if (drawY < 0)
                drawY = drawY + ct.getWorldHeight()*32;
            if(drawY > ct.getWorldHeight()*32)
                drawY = drawY%(ct.getWorldHeight()*32);
        }
        else
        {
            drawY = spriteEntity.getEntityMY() - mapRenderer.getMapY();
        }
       
    }
    
    public void render(Graphics grphcs)
    {
       
            renderable.draw(drawX, drawY);
        
    }

    
}
