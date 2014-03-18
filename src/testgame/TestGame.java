package testgame;

import ggf.*;
import java.awt.Color;

public class TestGame extends Game {

    public TestGame() {
        super();
        init("Dette er en test");
        start();
    }

    @Override
    protected void setupStates(GameStateManager stateManager) {
        stateManager.addState("HelloWorld", new HelloWorldState());
        stateManager.setCurrentState("HelloWorld");
    }
    
    @Override
    protected void setupGraphics(GameGraphicsManager graphics) {
        graphics.setWindowSize(800, 600);
        graphics.setBackgroundColor(Color.WHITE);
    }
    
    

    
    public static void main(String[] args) {
        new TestGame();
    }
}
