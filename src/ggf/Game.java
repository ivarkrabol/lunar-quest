package ggf;

import ggf.framework.GraphicsManager;
import ggf.framework.GameStateManager;
import ggf.framework.InputHandler;
import ggf.framework.GameTime;
import ggf.framework.Controls;
import java.awt.Graphics2D;

public abstract class Game {
    
    private static final int DEFAULT_FPS = 60;

    private GameTime time;
    private InputHandler input;
    private Controls controls;
    private GameStateManager stateMgr;
    private GraphicsManager graphicsMgr;
    private int framesPerSecond;
    
    protected Game() {
        framesPerSecond = DEFAULT_FPS;
    }
    
    protected void init(String windowTitle) {
        time = new GameTime(getFramesPerSecond());
        setupTime(time);
        
        input = new InputHandler();
        controls = new Controls(input);
        setupControls(input);
        
        stateMgr = new GameStateManager();
        setupStates(stateMgr);
        
        graphicsMgr = new GraphicsManager(windowTitle) {
            @Override
            protected void draw(Graphics2D g) {
                stateMgr.draw(g);
            }
            
            @Override
            protected void init() {
                addInputListeners(input, input);
            }
        };
        setupGraphics(graphicsMgr);
        
    }
    
    protected void start() {
        while(true)
        {
            update();
            repaint();
            
            int timeLeft = (int)time.frameTimeLeft();
            if (timeLeft < 10) timeLeft = 10;
            try {
                Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }
    
    private void update() {
        time.update();
        stateMgr.update(time, controls);
        input.update();
    }
    
    private void repaint() {
        graphicsMgr.repaint();
    }
    
    protected void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond; 
    }
    
    protected int getFramesPerSecond() {
        return framesPerSecond;
    }

    protected void setControls(Controls controls) {
        this.controls = controls;
    }
    
    protected void setupTime(GameTime time) {}
    protected void setupControls(InputHandler input) {}
    protected void setupStates(GameStateManager stateManager) {}
    protected void setupGraphics(GraphicsManager graphicsManager) {}
    
}