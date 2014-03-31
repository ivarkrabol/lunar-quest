package lunarquest;

import ggf.framework.Controls;
import ggf.framework.InputHandler;

public class LQControls extends Controls {

    public LQControls(InputHandler input) {
        super(input);
        
        bind("acc", LQConstants.KEY_ACC, InputType.KEY_DOWN);
        bind("rot_cw", LQConstants.KEY_ROT_CW, InputType.KEY_DOWN);
        bind("rot_ccw", LQConstants.KEY_ROT_CCW, InputType.KEY_DOWN);
        bind("zoom_in", LQConstants.KEY_ZOOM_IN, InputType.KEY_DOWN);
        bind("zoom_out", LQConstants.KEY_ZOOM_OUT, InputType.KEY_DOWN);
        bind("speed_up", LQConstants.KEY_SPEED_UP, InputType.KEY_DOWN);
        bind("slow_down", LQConstants.KEY_SLOW_DOWN, InputType.KEY_DOWN);
        bind("focus", LQConstants.KEY_FOCUS, InputType.KEY_PRESSED);
        bind("pause", LQConstants.KEY_PAUSE, InputType.KEY_PRESSED);
    }
}