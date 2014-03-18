package lunarquest;

import ggf.Game;
import ggf.GameClock;
import ggf.GameGraphicsManager;
import ggf.GameStateManager;
import java.awt.Color;

public class LunarQuest extends Game {
    
    private GameClock clock;

    public LunarQuest() {
        init("Lunar Quest");
        start();
    }
    
    @Override
    protected void setupClock(GameClock clock) {
        this.clock = clock;
    }
    
    @Override
    protected void setupGraphics(GameGraphicsManager graphicsManager) {
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
