package lunarquest;

import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics2D;

public class CelestialObject extends GravityObject {
    
    public static final double DENSITY_CONSTANT = 19000.0;
    
    private CircleObject visualObject;

    public CelestialObject(FrameOfReference parent, Vector pos, Vector vel, double radius) {
        super(parent, pos, 0, vel);
        setMaxRadius(radius);
        visualObject = new CircleObject(this, Vector.NULL, radius);
        
    }
    
    @Override
    public double getMass() {
        return Math.pow(getRadius(),3)*DENSITY_CONSTANT;
    }
    
    public double getRadius() {
        return visualObject.getRadius();
    }
    
    public void setRadius(double radius) {
        visualObject.setRadius(radius);
    }
    
    @Override
    public Color getFillColor() {
        return visualObject.getFillColor();
    }
    
    @Override
    public void setFillColor(Color color) {
        visualObject.setFillColor(color);
    }

    @Override
    public void drawDetailed(Graphics2D g) {
        visualObject.draw(g);
    }

}