
package towerdefense;

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
    
      
    private Image orbs, miniMapImage,berry, house, agent, enemyEntity;
    private TiledMap map;
    
    private SpriteRenderer enemyRenderer;
    
    private Entity enemy; 
    //private Enemy agentEntity, agentEntity1, agentEntity2;
   // private ArrayList<Enemy> agentArray;
    
    //private Sound orbSound, music;
   
    
    private double x, y;
    private int delta, orbPoints, enemyPoints;
    private CoordinateTranslator ct;
    
    private boolean exit, log, goRightKey, goLeftKey, goUpKey, goDownKey;
    private boolean Win, Lose, Locator, newMap;
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
        
        enemy   = new Enemy();
  
//        orbs = new Image("res/ball.png");
   //     berry = new Image("res/berry.png");
     //   house = new Image("res/home.png");
       
        
        enemyEntity = new Image("res/Cool.png");
        
        
        enemyRenderer = new SpriteRenderer(enemyEntity);
        
          
//        agent = new Image("res/steve.png");
        
        
        orbPoints = 0;
        enemyPoints = 0;
        
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
        
    
        enemy.update();
        
        //Updating coordinates for rendering
        enemyRenderer.update(mapRenderer, enemy);
        

        
        
        
        
       
       
        if(gc.getInput().isKeyPressed(Input.KEY_Q))
           gc.exit();

    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException 
    {
        
        //Map
        
        mapRenderer.render();
       
        
        //pointCount
        
        //Entities
         enemyRenderer.render(grphcs);
     
        //exit
        
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
    
    public int getWinCount()
    {
        return orbPoints;
    }
    
    public int getLoseCount()
    {
        return enemyPoints;
    }
    
    
    public int getDelta()
    {
        return delta;
    }
    
    public CoordinateTranslator getCT()
    {
        return ct;
    }
    
    
}