package lunarquest;

import ggf.GameInput;
import java.awt.event.KeyEvent;

public class FlyInput {
    
    private GameInput input;
    
    public FlyInput(GameInput input) {
        this.input = input;
    }
    
    public static final int KEY_ACC = KeyEvent.VK_W;
    public static final int KEY_ROT_CW = KeyEvent.VK_D;
    public static final int KEY_ROT_CCW = KeyEvent.VK_A;
    public static final int KEY_ZOOM_IN = KeyEvent.VK_UP;
    public static final int KEY_ZOOM_OUT = KeyEvent.VK_DOWN;
    public static final int KEY_SPEED_UP = KeyEvent.VK_PERIOD;
    public static final int KEY_SLOW_DOWN = KeyEvent.VK_COMMA;
    public static final int KEY_FOCUS = KeyEvent.VK_ENTER;
    public static final int KEY_PAUSE = KeyEvent.VK_SPACE;
    
    public boolean acc() {
        return input.isKeyDown(KEY_ACC);
    }
    public boolean rotCw() {
        return input.isKeyDown(KEY_ROT_CW);
    }
    public boolean rotCcw() {
        return input.isKeyDown(KEY_ROT_CCW);
    }
    public boolean zoomIn() {
        return input.isKeyDown(KEY_ZOOM_IN);
    }
    public boolean zoomOut() {
        return input.isKeyDown(KEY_ZOOM_OUT);
    }
    public boolean speedUp() {
        return input.isKeyDown(KEY_SPEED_UP);
    }
    public boolean slowDown() {
        return input.isKeyDown(KEY_SLOW_DOWN);
    }
    public boolean switchFocus() {
        return input.isKeyPressed(KEY_FOCUS);
    }
    public boolean togglePause() {
        return input.isKeyPressed(KEY_PAUSE);
    }
}
