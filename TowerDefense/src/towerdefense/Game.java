
package towerdefense;

import edu.moravian.math.CoordinateTranslator;
import edu.moravian.view.MapRenderer;
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
    
    private final int WIDTH = 900;
    private final int HEIGHT = 700;
    
      
    private Image orbs, miniMapImage,berry, house, agent, playerEntity;
    private TiledMap map;
    
    //private SpriteRenderer playerRenderer, agentRenderer, agentRenderer1, agentRenderer2, orbRenderer, berryRenderer, houseRenderer;
    private MapRenderer mapRenderer;
    //private Entity player, treasureEntity, berryEntity, houseEntity; 
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
  


        orbs = new Image("res/ball.png");
        berry = new Image("res/berry.png");
        house = new Image("res/home.png");
        
        
        
        
        
        
        playerEntity = new Image("res/Cool.png");
        
        
        //playerRenderer = new SpriteRenderer(playerEntity);
          
        agent = new Image("res/steve.png");
        
        
        
        
        
        
        
        mapRenderer = new MapRenderer(map);
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
       
               
        
        mapRenderer.update(x, y);
        x = mapRenderer.getX();
        y = mapRenderer.getY();
        
    
        
        
        //Updating coordinates for rendering
        

        
        
        
        
       
       
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