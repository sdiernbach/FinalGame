
package edu.moravian.stateMachine;

import edu.moravian.model.Bullet;



public interface AgentState 
{   
    public void Execute(Bullet bulletEntity);
    public void performAction(Bullet bulletEntity);
}
