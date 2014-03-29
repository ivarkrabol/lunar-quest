package lunarquest;

import ggf.Game;
import ggf.framework.GameTime;
import ggf.framework.GraphicsManager;
import ggf.framework.GameStateManager;
import ggf.framework.InputHandler;
import java.awt.Color;

public class LunarQuest extends Game {
    
    private GameTime clock;

    public LunarQuest() {
        init("Lunar Quest");
        start();
    }
    
    @Override
    protected void setupTime(GameTime clock) {
        this.clock = clock;
    }

    @Override
    protected void setupControls(InputHandler input) {
        setControls(new LQControls(input));
    }
    
    @Override
    protected void setupGraphics(GraphicsManager graphicsManager) {
        graphicsManager.setWindowSize(LQConstants.WINDOW_WIDTH, LQConstants.WINDOW_HEIGHT);
        graphicsManager.setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void setupStates(GameStateManager stateManager) {
        stateManager.addState("FlyState", new FlyState(clock));
        stateManager.setCurrentState("FlyState");
    }
    
    public static void main(String[] args) {
        new LunarQuest();
    }
    
}
