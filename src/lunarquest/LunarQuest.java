package lunarquest;

import ggf.Game;
import ggf.GameGraphicsManager;
import ggf.GameStateManager;
import java.awt.Color;
import javax.swing.SwingUtilities;

public class LunarQuest extends Game {

    public LunarQuest() {
        init("Lunar Quest");
        start();
    }
    
    @Override
    protected void setupGraphics(GameGraphicsManager graphicsManager) {
        graphicsManager.setWindowSize(LQConstants.WINDOW_WIDTH, LQConstants.WINDOW_HEIGHT);
        graphicsManager.setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void setupStates(GameStateManager stateManager) {
        stateManager.addState("FlyState", new FlyState());
        stateManager.setCurrentState("FlyState");
    }
    
    public static void main(String[] args) {
        new LunarQuest();
    }
    
}
