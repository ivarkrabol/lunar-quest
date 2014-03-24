package lunarquest;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class LQConstants {
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;
    
    public static final Color COLOR_BLUE = new Color(57, 103, 199);
    public static final Color COLOR_GREEN = new Color(115, 194, 124);
    public static final Color COLOR_BEIGE = new Color(242, 239, 207);
    public static final Color COLOR_WHITEISH = new Color(230, 230, 230);
    public static final Color COLOR_LIGHT_GRAY = new Color(180, 180, 180);
    
    public static final int KEY_ACC = KeyEvent.VK_W;
    public static final int KEY_ROT_CW = KeyEvent.VK_D;
    public static final int KEY_ROT_CCW = KeyEvent.VK_A;
    public static final int KEY_ZOOM_IN = KeyEvent.VK_UP;
    public static final int KEY_ZOOM_OUT = KeyEvent.VK_DOWN;
    public static final int KEY_SPEED_UP = KeyEvent.VK_RIGHT;
    public static final int KEY_SLOW_DOWN = KeyEvent.VK_LEFT;
    public static final int KEY_FOCUS = KeyEvent.VK_ENTER;
    public static final int KEY_PAUSE = KeyEvent.VK_SPACE;
}
