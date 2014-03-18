package ggf;

import java.awt.Graphics;
import javax.swing.JPanel;

public class GameObject {
    
    protected Vector pos;
    
    public GameObject(Vector pos) {
        this.pos = pos;
    }

    public Vector getPos() {
        return pos;
    }
    
    public void setPos(Vector pos) {
        this.pos = pos;
    }
    
    public void update(GameClock clock, GameStateManager gsm, GameInput input) { }
    
    public void draw(Graphics graphics) { }

}
