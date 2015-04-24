
package edu.moravian.view;
import towerdefense.Game;
import edu.moravian.math.CoordinateTranslator;
import java.awt.Point;
import org.newdawn.slick.tiled.TiledMap;


public class MapRenderer 
{
    private int mapX, mapY, plotX, plotY, sx, sy, tx, ty;
    private double x, y;
    private final CoordinateTranslator ct;
    private final TiledMap map;

    public MapRenderer(TiledMap map)
    {
        ct = Game.getInstance().getCT();
        this. map = map;
    }
    
    public void update(double x, double y)
    {
        this.x = x;
        this.y = y;
        Point mapCo = ct.worldToScreen(x, y);
        mapX = (int) mapCo.getX();
        mapY = (int) mapCo.getY() - ct.getScreenHeight();
        tx = (mapX%32);
        ty = (mapY%32);
        plotX = (int) (-1*tx);
        plotY = (int) (-1*ty);
        sx = (int) (mapX/32);
        sy = (int) (mapY/32);
        
        if(mapX < -ct.getScreenWidth()/2)
        {
            this.x = ((ct.getWorldWidth()*32 - ct.getScreenWidth()/2)*ct.getWorldWidth())/(double)ct.getScreenWidth();
        }
        if(mapX > ct.getWorldWidth()*32 - ct.getScreenWidth()/2)
        {
            this.x = -(double)ct.getWorldWidth()/2;
        }
        if(mapY < -ct.getScreenHeight()/2)
        {
            this.y = (ct.getWorldHeight()*32 - ct.getScreenHeight()/2)*ct.getWorldHeight()/-(double)ct.getScreenHeight();
        }
        if(mapY > ct.getWorldHeight()*32 - ct.getScreenHeight()/2)
        {
            this.y = (ct.getScreenHeight()/2*ct.getWorldHeight())/ct.getScreenHeight();
        }
    }
    
    public void render()
    {
        map.render((int) plotX, (int) plotY, (int) sx,(int) sy, ct.getScreenWidth()/32 + 2, ct.getScreenHeight()/32 + 2);
        if(mapX > map.getWidth()*32 - ct.getScreenWidth())
        {
            map.render((int) plotX, (int) plotY, (int) (sx - map.getWidth())%(map.getWidth()), (int) sy, ct.getScreenWidth()/32 + 2, ct.getScreenHeight()/32 + 2);
        }
        if(mapX < 0)
        {
            map.render((int) plotX - 32, (int) plotY, (int) (sx + map.getWidth() - 1)%(map.getWidth()), (int) sy, ct.getScreenWidth()/32 + 2, ct.getScreenHeight()/32 + 2);
        }
        if(mapY < 0)
        {
            map.render((int) plotX, (int) plotY - 32, (int) sx, (int) (sy + map.getHeight() - 1)%(map.getHeight()), ct.getScreenWidth()/32 + 2, ct.getScreenHeight()/32 + 2);
        }
        if(mapY > map.getHeight()*32 - ct.getScreenHeight())
        {
            map.render((int) plotX, (int) plotY, (int) sx, (int) (sy - map.getHeight())%(map.getHeight()), ct.getScreenWidth()/32 + 2, ct.getScreenHeight()/32 + 2);
        }
        if(mapX < 0 && mapY < 0)
        {
            map.render((int) plotX - 32, (int) plotY - 32, (int) (sx + map.getWidth() - 1)%(map.getWidth()), (int) (sy + map.getHeight() - 1)%(map.getHeight()), ct.getScreenWidth()/32 + 2, ct.getScreenHeight()/32 + 2);
        }
        if(mapX < 0 && mapY > map.getHeight()*32 - ct.getScreenHeight())
        {
            map.render((int) plotX - 32, (int) plotY, (int) (sx + map.getWidth() - 1)%(map.getWidth()), (int) (sy - map.getHeight())%(map.getHeight()), ct.getScreenWidth()/32 + 2, ct.getScreenHeight()/32 + 2);
        }
        if(mapX > map.getWidth()*32 - ct.getScreenWidth() && mapY < 0)
        {
            map.render((int) plotX, (int) plotY - 32, (int) (sx - map.getWidth())%(map.getWidth()), (int) (sy + map.getHeight() - 1)%(map.getHeight()), ct.getScreenWidth()/32 + 2, ct.getScreenHeight()/32 + 2);
        }
        if(mapX > map.getWidth()*32 - ct.getScreenWidth() && mapY > map.getHeight()*32 - ct.getScreenHeight())
        {
            map.render((int) plotX, (int) plotY, (int) (sx - map.getWidth())%(map.getWidth()), (int) (sy - map.getHeight())%(map.getHeight()), ct.getScreenWidth()/32 + 2, ct.getScreenHeight()/32 + 2);
        }
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }
}
