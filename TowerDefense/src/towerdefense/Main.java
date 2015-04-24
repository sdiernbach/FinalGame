
package towerdefense;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Main 
{
    public static void main(String[]args) throws SlickException
    {
        
        
        try
        {
            Game play = Game.getInstance();
            AppGameContainer agc = new AppGameContainer(play, play.getScreenWidth(), play.getScreenHeight(), false);
            agc.start();
        }
        catch(SlickException ex)
        {
            System.out.println("Error starting game");
        }
    }
}
