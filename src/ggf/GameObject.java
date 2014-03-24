package ggf;

import ggf.geom.Vector;
import java.awt.Graphics2D;

public class GameObject {
    
    private Vector pos;
    
    public GameObject(Vector pos) {
        this.pos = pos;
    }

    public Vector getPos() {
        return new Vector(pos);
    }
    
    public void setPos(Vector pos) {
        this.pos = pos;
    }
    
    public void setPos(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getX() {
        return pos.getX();
    }

    public void setX(double x) {
        pos.setX(x);
    }

    public double getY() {
        return pos.getX();
    }

    public void setY(double y) {
        pos.setY(y);
    }
    
    public void update(GameClock clock, GameStateManager gsm, GameInput input) { }
    
    public void draw(Graphics2D g) {}

}
