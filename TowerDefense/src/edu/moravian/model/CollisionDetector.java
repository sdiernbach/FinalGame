
package edu.moravian.model;


public class CollisionDetector 
{
    public static boolean checkCollision(double x1, double y1, double x2, double y2)
    {
        return (5+5)*(50+50) > (Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}
