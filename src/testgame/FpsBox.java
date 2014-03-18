package testgame;

import ggf.GameClock;
import ggf.GameInput;
import ggf.GameObject;
import ggf.GameRectangle;
import ggf.GameStateManager;
import ggf.GameString;
import ggf.Rectangle;
import ggf.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class FpsBox extends GameObject {
    
    private GameRectangle box;
    private GameString fpsString;

    public FpsBox() {
        super(new Vector(0, 0));
        box = new GameRectangle(new Rectangle(0, 0, 100, 30), Color.DARK_GRAY);
        fpsString = new GameString(new Vector(10, 22), "FPS: ", Color.WHITE, new Font("Arial", Font.PLAIN, 22));
    }

    @Override
    public void draw(Graphics g) {
        box.draw(g);
        fpsString.draw(g);
    }

    @Override
    public void update(GameClock clock, GameStateManager gsm, GameInput input) {
        fpsString.setString("FPS: "+clock.getTrueFPS());
    }
    
    

}
