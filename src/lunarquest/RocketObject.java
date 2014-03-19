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
        rocket.setScale(3);
        rocket.setPoints(
                new double[]{ 0.10, 0.00,-0.10, 0.00},
                new double[]{ 0.25, 0.15, 0.25,-0.25});
        
        CircleObject window = new CircleObject(this, Vector.NULL, 1);
        window.setFillColor(Color.DARK_GRAY);
        window.setScale(rotation);
        
        visualObjects = new TransformObject[]{window, rocket};
        
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
