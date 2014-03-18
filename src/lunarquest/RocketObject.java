package lunarquest;

import ggf.Vector;
import java.awt.Color;
import java.awt.Graphics;

public class RocketObject extends GravityObject {

    private TransformObject[] visualObjects;
    
    public RocketObject(FrameOfReference parent, Vector pos, double rotation, Vector vel) {
        super(parent, pos, rotation, vel);
        
        PolygonObject rocket = new PolygonObject(this, Vector.NULL, 0, 4);
        rocket.setFillColor(Color.WHITE);
        rocket.setScale(3);
        rocket.setPoints(
                new double[]{ 10.0,  0.0,-10.0,  0.0},
                new double[]{ 25.0, 15.0, 25.0,-25.0});
        
        CircleObject window = new CircleObject(this, Vector.NULL, 1);
        window.setFillColor(Color.DARK_GRAY);
        window.setScale(rotation);
        
        visualObjects = new TransformObject[]{window, rocket};
        
    }
    
    @Override
    public double getMass() {
        return 50000;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for(TransformObject detail : visualObjects) {
            detail.draw(g);
        }
    }
    
}
