package lunarquest;

import ggf.framework.Controls;
import ggf.framework.InputHandler;

public class LQControls extends Controls {

    public LQControls(InputHandler input) {
        super(input);
        
        bindKeyDown("acc", LQConstants.KEY_ACC);
        bindKeyDown("rot_cw", LQConstants.KEY_ROT_CW);
        bindKeyDown("rot_ccw", LQConstants.KEY_ROT_CCW);
        bindKeyDown("zoom_in", LQConstants.KEY_ZOOM_IN);
        bindKeyDown("zoom_out", LQConstants.KEY_ZOOM_OUT);
        bindKeyDown("speed_up", LQConstants.KEY_SPEED_UP);
        bindKeyDown("slow_down", LQConstants.KEY_SLOW_DOWN);
        bindKeyPressed("focus", LQConstants.KEY_FOCUS);
        bindKeyPressed("pause", LQConstants.KEY_PAUSE);
    }
}