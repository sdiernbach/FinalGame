
package edu.moravian.math;

public class Distance {
    private double distance;
    public Distance()
    {
    }
    
    public double Distance (Point2D from, Point2D to)
    {
        double dx =to.getX()-from.getX();
        double dy = to.getY()-from.getY();
        
        distance = Math.sqrt((dx*dx)+(dy*dy));
        return distance;
    }
}
