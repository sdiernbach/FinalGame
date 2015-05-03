
package edu.moravian.stateMachine;

import edu.moravian.model.Enemy;


public interface AgentState 
{   
    public void Execute(Enemy agentEntity);
    public void performAction(Enemy agentEntity);
}
