
package towerdefense;

import Factory.SimpleFactory;
import Factory.enemyFactory;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.model.Berry;
import edu.moravian.model.Enemy;
import edu.moravian.view.MapRenderer;
import edu.moravian.view.SpriteRenderer;
import edu.moravian.model.Entity;
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
    
      
    private Image enemyEntity;
    private TiledMap map;
    
    private SpriteRenderer enemyRenderer;
    
    private Entity enemy; 
    //private Enemy agentEntity, agentEntity1, agentEntity2;
   // private ArrayList<Enemy> agentArray;
    
   
   
    
    private double x, y;
    private int delta;
    public int healthPoints;
    private CoordinateTranslator ct;
    private SimpleFactory factory;
    
    private boolean exit, log, goRightKey, goLeftKey, goUpKey, goDownKey;
    private boolean Win, Lose;
    private boolean agentGoUp, agentGoDown, agentGoLeft, agentGoRight;
    
    private Game(String title)
    {
        super(title);
    }
    
    public static Game getInstance()
    {
        if(instance == null)
            instance = new Game("TowerDefense");
        return instance;
    }

    @Override
    public void init(GameContainer gc) throws SlickException 
    {  
       
        map = new TiledMap("res/towerPath.tmx");
        ct = new CoordinateTranslator(map.getWidth(), map.getHeight(), WIDTH, HEIGHT, 0, 0);
        mapRenderer = new MapRenderer(map);
        factory = new SimpleFactory(100);
        factory.getClanMembers();
  
//        orbs = new Image("res/ball.png");
   //     berry = new Image("res/berry.png");
     //   house = new Image("res/home.png");
       
        
        enemyEntity = new Image("res/Cool.png");
        enemyRenderer = new SpriteRenderer(enemyEntity);
//        agent = new Image("res/steve.png");
        
        
        healthPoints = 100;
        
        
        x = 0;
        y = 0;
        
       
        exit = false;
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
        
        //Enemies in array
       for(int i=0; i<factory.arraySize();i++)
            factory.getEnemyAt(i).update();
         
        
        
        //Updating coordinates for rendering
        for(int i=0; i<factory.arraySize();i++)
            enemyRenderer.update(factory.getEnemyAt(i));
        
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
         enemyRenderer.render(grphcs);
     
         grphcs.drawString("Health Points: "+ healthPoints, 300, 50);
         
        //exit
         if(Lose==true)
        {
            grphcs.clear();
            grphcs.drawString("You LOSE", 400, 300);
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
    
}