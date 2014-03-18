package testgame;

import ggf.GameClock;
import ggf.GameInput;
import ggf.GameState;
import ggf.GameStateManager;
import java.awt.Graphics2D;

public class HelloWorldState extends GameState {
    
    private HelloWorldString helloWorld;
    private FpsBox fpsBox;
    
    public HelloWorldState() {
        helloWorld = new HelloWorldString();
        fpsBox = new FpsBox();
        
    }

    @Override
    public void update(GameClock clock, GameStateManager gsm, GameInput input) {
        helloWorld.update(clock, gsm, input);
        fpsBox.update(clock, gsm, input);
    }

    @Override
    public void draw(Graphics2D g) {
        helloWorld.draw(g);
        fpsBox.draw(g);
    }
    
    
    
}
