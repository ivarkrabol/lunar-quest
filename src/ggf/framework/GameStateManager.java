package ggf.framework;

import ggf.GameState;
import java.awt.Graphics2D;
import java.util.HashMap;

public class GameStateManager {
    
    private GameState currentState;
    private HashMap<String, GameState> gameStates;

    public GameStateManager() {
        currentState = GameState.NO_STATE;
        gameStates = new HashMap();
    }
    
    public final GameState getCurrentState() {
        return currentState;
    }
    
    public void addState(String stateName, GameState state) {
        gameStates.put(stateName, state);
    }
    
    public boolean hasState(String stateName) {
        return gameStates.containsKey(stateName);
    }
    
    public final void setCurrentState(GameState state) {
        currentState.onLeave(state);
        state.onEnter(currentState);
        currentState = state;
    }
    
    public final void setCurrentState(String stateName) {
        GameState state = gameStates.get(stateName);
        setCurrentState(state);
    }
    
    public final void update(GameTime time, Controls controls) {
        currentState.update(time, this, controls);
    }
    
    public final void draw(Graphics2D g) {
        currentState.draw(g);
    }
    
}
