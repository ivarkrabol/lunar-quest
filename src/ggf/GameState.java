package ggf;

import java.awt.Graphics2D;

public abstract class GameState {
    
    public static final GameState NO_STATE = new GameState() {};
    
    public void init() { }
    public void update(GameTime clock, GameStateManager stateManager, GameInput input) { }
    public void draw(Graphics2D graphics) { }
    public void onEnter(GameState previousState) { }
    public void onLeave(GameState nextState) { }
}
