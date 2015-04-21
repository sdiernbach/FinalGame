
package edu.moravian.math;

import edu.moravian.math.AStar.AStar;
import edu.moravian.math.AStar.AreaMap;
import edu.moravian.math.AStar.Path;
import edu.moravian.math.heuristic.AStarHeuristic;
import edu.moravian.math.heuristic.ClosestHeuristic;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


public class PathFinder {
    private TiledMap map;
    private AreaMap myMap;
    private int dumb [][];
    AStarHeuristic heur = new ClosestHeuristic();
    AStar astar;
    Path p;
   
    public PathFinder() throws SlickException
    {
        map = new TiledMap("res/Map2.tmx");
        dumb = new int [map.getWidth()][map.getHeight()];
        /*for (int r=0; r<map.getHeight(); r++) {
              for (int c=0; c<map.getWidth(); c++)
              {
                  if(map.getTileId(r, c, 1)==11)
                      dumb[r][c]= 1;
              }
        }*/
    }
    public void generatePath(Point2D from, Point2D to)
    {
        System.out.println("tiledId "+map.getTileId(7, 5, 1));
        System.out.println("layerIndex "+map.getLayerIndex("object"));
        myMap = new AreaMap(map.getHeight(),map.getWidth(), dumb);
        astar = new AStar(myMap,heur);
        p=astar.calcShortestPath(from.getX(),from.getY(), to.getX(), to.getY());
        astar.printPath();
    }
    
    public Path getPath()
    {
        return p;
    }
}
