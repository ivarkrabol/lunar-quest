package ggf;

import java.awt.Graphics2D;

public abstract class Game {
    
    private static final int DEFAULT_FPS = 60;

    private GameClock clock;
    private GameInput input;
    private GameStateManager stateManager;
    private GameGraphicsManager graphicsManager;
    private int framesPerSecond;
    
    protected Game() {
        framesPerSecond = DEFAULT_FPS;
    }
    
    protected void init(String windowTitle) {
        clock = new GameClock(getFramesPerSecond());
        setupClock(clock);
        input = new GameInput();
        stateManager = new GameStateManager();
        setupStates(stateManager);
        graphicsManager = new GameGraphicsManager(windowTitle) {
            @Override
            protected void draw(Graphics2D graphics2D) {
                stateManager.draw(graphics2D);
            }
        };
        setupGraphics(graphicsManager);
        graphicsManager.addInputListeners(input, input);
        graphicsManager.init();
    }
    
    protected void start() {
        while(true)
        {
            update();
            repaint();
            
            int timeLeft = (int)clock.frameTimeLeft();
            if (timeLeft < 10) timeLeft = 10;
            try {
                Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }
    
    private void update() {
        clock.update();
        stateManager.update(clock, input);
        input.update();
    }
    
    private void repaint() {
        graphicsManager.repaint();
    }
    
    protected void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond; 
    }
    
    protected int getFramesPerSecond() {
        return framesPerSecond;
    }
    
    protected void setupClock(GameClock clock) {}
    protected void setupStates(GameStateManager stateManager) {}
    protected void setupGraphics(GameGraphicsManager graphicsManager) {}
    
}