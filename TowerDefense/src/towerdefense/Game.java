
package towerdefense;

import Factory.SimpleFactory;
import Factory.enemyFactory;
import edu.moravian.math.AStar.AStar;
import edu.moravian.math.AStar.AreaMap;
import edu.moravian.math.AStar.Node;
import edu.moravian.math.AStar.Path;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.PathFinder;
import edu.moravian.math.heuristic.AStarHeuristic;
import edu.moravian.math.heuristic.ClosestHeuristic;
import edu.moravian.model.Tower;
import edu.moravian.model.Enemy;
import edu.moravian.view.MapRenderer;
import edu.moravian.view.SpriteRenderer;
import edu.moravian.model.Entity;
import edu.moravian.model.TowerList;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;


public class Game extends BasicGame
{
    private static Game instance;
    private MapRenderer mapRenderer;
    
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    
      
    private Image enemyEntity, towerEntity;
    private TiledMap map;
    
    private SpriteRenderer enemyRenderer, towerRenderer;
    
    private Entity enemy, tower; 
   
  
    
    private PathFinder pf;
    private Path p;
    AStarHeuristic heur = new ClosestHeuristic();
    AStar astar;
    private int Dmap [][];
    private AreaMap myMap;
    
    
    private double x, y;
    private int delta, enemyLength, spawnTime, timeSinceLastSpawn;
    public int healthPoints;
    private CoordinateTranslator ct;
    private SimpleFactory factory;
    private TowerList towerList;
    
    private boolean exit, log, goRightKey, goLeftKey, goUpKey, goDownKey;
    private boolean Win, Lose;
    private boolean agentGoUp, agentGoDown, agentGoLeft, agentGoRight;
    
    private Game(String title) throws SlickException
    {
        super(title);
      
    }
    
    public static Game getInstance() throws SlickException
    {
        if(instance == null)
            instance = new Game("TowerDefense");
        return instance;
    }

    @Override
    public void init(GameContainer gc) throws SlickException 
    {  
       this.pf = new PathFinder();
        map = new TiledMap("res/towerPath.tmx");
        ct = new CoordinateTranslator(map.getWidth(), map.getHeight(), WIDTH, HEIGHT, 0, 0);
        mapRenderer = new MapRenderer(map);
        factory = new SimpleFactory(100);
        factory.getEnemies();
        factory.getEnemyRenderer();
        
        enemyEntity = new Image("res/Cool.png");
        enemyRenderer = new SpriteRenderer(enemyEntity);
        enemyLength=0;
        spawnTime=5;
        timeSinceLastSpawn=0;
  
//        orbs = new Image("res/ball.png");
   //     berry = new Image("res/berry.png");
     //   house = new Image("res/home.png");
       
        Dmap = new int [map.getWidth()][map.getHeight()];
        for (int r=0; r<map.getHeight(); r++) {
              for (int c=0; c<map.getWidth(); c++)
              {
                  if(map.getTileId(r, c, 1)==118)
                      Dmap[r][c]= 1;
              }
        }
        
        
        myMap = new AreaMap(map.getHeight(),map.getWidth(), Dmap);
        astar = new AStar(myMap,heur);
        astar.printPath();
        
        
       // enemyEntity = new Image("res/Cool.png");
        //factory.getEnemyRenderAt(0) = new SpriteRender(enemyEntity);
        //enemyRenderer = new SpriteRenderer(enemyEntity);
        towerEntity = new Image("res/steve.png");
        towerRenderer = new SpriteRenderer(towerEntity);
        
        
        healthPoints = 100;
        
        
        x = 0;
        y = 0;
        
       
        exit = false;
    }
    
    @Override
    public void mousePressed(int button, int x, int y)
    {
        x=x/32;
        y=y/32;
        if(Dmap[x][y]==0)
        {
            myMap.setTowerLocation(x, y);
            Dmap[x][y]=2;
            astar.printPath();
            
        }
        else if(Dmap[x][y]==2)
        {
            myMap.removeTowerLocation(x, y);
            Dmap[x][y]=0;
            astar.printPath();
        }
       
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException 
    {
           
        this.delta = delta;
        Input input = gc.getInput();
       
               
        mapRenderer = new MapRenderer(map);
        mapRenderer.update(x, y);
        x = mapRenderer.getX();
        y = mapRenderer.getY();
        
        //for tower no update needed yet
        
        towerList.update(delta);
        towerRenderer.update(tower);

        
        //Enemies in array
       //for(int i=0; i<=factory.arraySize();i++)
      // {
           if (enemyLength == factory.arraySize())
               enemyLength = 0;
          

           if (timeSinceLastSpawn>spawnTime) {
               factory.getEnemyAt(enemyLength).update();
               timeSinceLastSpawn=0; 
           }
           else
               timeSinceLastSpawn+=delta;
           factory.getEnemyRenderAt(enemyLength).update(factory.getEnemyAt(enemyLength));
           
       //}
       
        
        
       
       enemyLength++;
       
        //Quits game in a button
       if(gc.getInput().isKeyPressed(Input.KEY_Q))
          gc.exit();
        
        //Lose the game when health hits zero
       if(healthPoints==0)
       {
           Lose=true;
       
           gc.pause();
       }
        

    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException 
    {
        
        //Map
        
        mapRenderer.render();
       
        //pointCount
        
        //Entities
       
        //enemyRenderer.render(grphcs);
        for(int i=0;i<factory.arraySize();i++)
            enemyEntity.draw(factory.getEnemyAt(i).getEntityMX(), factory.getEnemyAt(i).getEntityMY());
            
        
         //towerRenderer.render(grphcs);
         
     
        grphcs.drawString("Health Points: "+ healthPoints, 300, 50);
         
        //exit
       
         
        for (int r=0; r<map.getHeight(); r++) {
              for (int c=0; c<map.getWidth(); c++)
              {
                  if(Dmap[r][c]== 2)
                      towerEntity.draw(r*32, c*32,200,200);
              }
        }
          if(Lose==true)
        {
            grphcs.clear();
            grphcs.drawString("You LOSE", 400, 400);
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
    
    public int getScreenWidth()
    {
        return WIDTH;
    }
    
    public int getScreenHeight()
    {
        return HEIGHT;
    }
    
    public int getWorldWidth()
    {
        return map.getWidth();
    }
    
    public int getWorldHeight()
    {
        return map.getHeight();
    }
    
    public int getHealthCount()
    {
        return healthPoints;
    }
    
    
    public int getDelta()
    {
        return delta;
    }
    
    public CoordinateTranslator getCT()
    {
        return ct;
    }
    
    public Entity getEnemyEntity()
    {
        return enemy;
    }
    
    public AreaMap getMap()
    {
            return myMap;
    }
    
    public int[][] getTower()
    {
        return Dmap;
    }
}