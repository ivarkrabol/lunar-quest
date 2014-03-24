package ggf;

import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameString extends GameObject {

    private String string;
    private Color color;
    private Font font;
    
    public GameString(Vector pos, String string, Color color, Font font) {
        super(pos);
        this.string = string;
        this.color = color;
        this.font = font;
    }
    
    public GameString(Vector pos, String string, Color color) {
        this(pos, string, color, null);
    }
    
    public GameString(Vector pos, String string) {
        this(pos, string, null, null);
    }
    
    public GameString(String string) {
        this(new Vector(0, 0), string, null, null);
    }
    
    public GameString() {
        this(new Vector(0, 0), "", null, null);
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        if(color != null && !g.getColor().equals(color)) g.setColor(color);
        if(font != null && !g.getFont().equals(font)) g.setFont(font);
        
        g.drawString(string, (int)getX(), (int)getY());
    }
    
    
}
