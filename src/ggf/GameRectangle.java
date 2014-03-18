package ggf;

import java.awt.Color;
import java.awt.Graphics;

public class GameRectangle extends GameObject {
    
    private Vector diag;
    private Color color;

    public GameRectangle(Rectangle rect, Color color) {
        super(rect.pos);
        diag = rect.diag;
        this.color = color;
    }

    public Vector getDiag() {
        return diag;
    }

    public void setDiag(Vector diag) {
        this.diag = diag;
    }
    
    public Rectangle getRect() {
        return new Rectangle(pos, diag);
    }
    
    public void setRect(Rectangle rect) {
        pos = rect.pos;
        diag = rect.diag;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect((int)pos.x, (int)pos.y, (int)diag.x, (int)diag.y);
    }
    
    

}
