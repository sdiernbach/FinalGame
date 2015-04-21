
package edu.moravian.math;

import java.awt.Point;


public class CoordinateTranslator 
{
    final int worldWidth, worldHeight, screenWidth, screenHeight, wllX, wllY;
    
    public CoordinateTranslator(int worldWidth, int worldHeight, int screenWidth, int screenHeight, int wllX, int wllY)
    {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.wllX = wllX;
        this.wllY = wllY;
    }
    
    public Point2D screenToWorld(int x, int y)
    {
        double worldX = x*worldWidth/screenWidth + wllX;
        double worldY = worldHeight + wllY - ((worldHeight*y)/screenHeight);
        Point2D returnPoint = new Point2D(worldX, worldY);
        return returnPoint;
    }

    public Point2D screenToWorld(Point p)
    {
        double worldX = p.getX()*worldWidth/screenWidth + wllX;
        double worldY = worldHeight + wllY - ((worldHeight*p.getY())/screenHeight);
        Point2D returnPoint = new Point2D(worldX, worldY);
        return returnPoint;
    }
    
    public Point worldToScreen(double x, double y)
    {
        int screenX = (int) ((x - wllX)*screenWidth/worldWidth);
        int screenY = (int) ((worldHeight - y + wllY)*screenHeight/worldHeight);
        Point returnPoint = new Point(screenX, screenY);
        return returnPoint;
    }
    
    public Point worldToScreen(Point2D p)
    {
        int screenX = (int) ((p.getX() - wllX)*screenWidth/worldWidth);
        int screenY = (int) ((worldHeight - p.getY() + wllY)*screenHeight/worldHeight);
        Point returnPoint = new Point(screenX, screenY);
        return returnPoint;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
