
package edu.moravian.stateMachine;
import edu.moravian.model.Bullet;



public class StateMachine 
{
    private final Bullet bulletEntity;
    private AgentState currentState, previousState, globalState;
    
    public StateMachine(Bullet bulletEntity)
    {
        this.bulletEntity = bulletEntity;
    }
    
    public void setCurrentState(AgentState state)
    {
        this.currentState = state;
    }

    public void setPreviousState(AgentState state) {
        this.previousState = state;
    }

    public void setGlobalState(AgentState state) {
        this.globalState = state;
    }
    
    public void update()
    {
        if(globalState!=null)
            globalState.Execute(bulletEntity);
        if(currentState!=null)
            currentState.Execute(bulletEntity);
    }
    
    public void changeState(AgentState newState)
    {
        previousState = currentState;
        currentState = newState;
    }
    
    public void revertToPreviousState()
    {
        this.changeState(previousState);
    }

    public AgentState getCurrentState() {
        return currentState;
    }

    public AgentState getPreviousState() {
        return previousState;
    }

    public AgentState getGlobalState() {
        return globalState;
    }
}
