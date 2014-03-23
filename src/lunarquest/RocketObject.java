package lunarquest;

import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics;

public class RocketObject extends GravityObject {

    private TransformObject[] visualObjects;
    
    public RocketObject(FrameOfReference parent, Vector pos, double rotation, Vector vel) {
        super(parent, pos, rotation, vel);
        
        PolygonObject rocket = new PolygonObject(this, Vector.NULL, 0, 4);
        rocket.setFillColor(Color.WHITE);
        rocket.setPoints(
                new double[]{ 0.10, 0.00,-0.10, 0.00},
                new double[]{ 0.25, 0.15, 0.25,-0.25});
        
        CircleObject window = new CircleObject(this, new Vector(0, 0.07), 0.04);
        window.setFillColor(Color.DARK_GRAY);
        
        visualObjects = new TransformObject[]{rocket, window};
        
    }
    
    @Override
    public double getMass() {
        return 0.05;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for(TransformObject detail : visualObjects) {
            detail.draw(g);
        }
    }
    
}
