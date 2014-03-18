package testgame;

import ggf.GameClock;
import ggf.GameInput;
import ggf.GameStateManager;
import ggf.GameString;
import ggf.Vector;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class HelloWorldString extends GameString {
    private Point center;

    public HelloWorldString() {
        super("Hello World");
        center = new Point(400, 300);
        pos = new Vector(400, 200);
    }

    @Override
    public void update(GameClock clock, GameStateManager gsm, GameInput input) {
        if(input.isKeyDown(KeyEvent.VK_RIGHT)) center.x += 0.5*clock.deltaTime();
        if(input.isKeyDown(KeyEvent.VK_LEFT)) center.x -= 0.5*clock.deltaTime();
        if(input.isKeyDown(KeyEvent.VK_DOWN)) center.y += 0.5*clock.deltaTime();
        if(input.isKeyDown(KeyEvent.VK_UP)) center.y -= 0.5*clock.deltaTime();
        
        pos.x = center.x + (int)(100*Math.cos((double)clock.runTime()/200.0));
        pos.y = center.y + (int)(100*Math.sin((double)clock.runTime()/200.0));
    }
    
    

}
