package lunarquest;

import ggf.GameInput;

public class FlyInput {
    
    private GameInput input;
    
    public FlyInput(GameInput input) {
        this.input = input;
    }
    
    public boolean acc() {
        return input.isKeyDown(LQConstants.KEY_ACC);
    }
    public boolean rotCw() {
        return input.isKeyDown(LQConstants.KEY_ROT_CW);
    }
    public boolean rotCcw() {
        return input.isKeyDown(LQConstants.KEY_ROT_CCW);
    }
    public boolean zoomIn() {
        return input.isKeyDown(LQConstants.KEY_ZOOM_IN);
    }
    public boolean zoomOut() {
        return input.isKeyDown(LQConstants.KEY_ZOOM_OUT);
    }
    public boolean speedUp() {
        return input.isKeyDown(LQConstants.KEY_SPEED_UP);
    }
    public boolean slowDown() {
        return input.isKeyDown(LQConstants.KEY_SLOW_DOWN);
    }
    public boolean switchFocus() {
        return input.isKeyPressed(LQConstants.KEY_FOCUS);
    }
    public boolean togglePause() {
        return input.isKeyPressed(LQConstants.KEY_PAUSE);
    }
}