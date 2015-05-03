
package edu.moravian.model;

import edu.moravian.stateMachine.AgentState;
import java.awt.Point;
import org.newdawn.slick.SlickException;


public class Bullet extends Entity {
     private AgentState currentState;

    public Bullet() throws SlickException
    {
       
    }
    
    @Override
    public void update() {
        
    }

    @Override
    public void reset() 
    {
        
    }
    
    public void changeState(AgentState newState)
    {
        currentState = newState;
    }
    
}
